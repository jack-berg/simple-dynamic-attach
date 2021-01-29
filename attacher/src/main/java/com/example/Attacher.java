package com.example;

import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.util.List;

public class Attacher {

    public static void main(String[] args) {
        var includeList = List.of("com.example");
        var agentLocation = System.getProperty("agent_location");

        System.out.println("Getting java vms.");
        VirtualMachine.list().forEach(vm -> {
            var displayName = vm.displayName();
            var pid = vm.id();

            // Ignore vms we can't attach to
            if (includeList.stream().noneMatch(displayName::contains)
                    || String.valueOf(ProcessHandle.current().pid()).equals(pid)
                    || displayName.isBlank()) {
                System.out.printf("Ignoring id: %s; displayName: %s%n", pid, displayName);
                return;
            }

            // Attach to the vm
            VirtualMachine virtualMachine = null;
            try {
                System.out.printf("Attaching to id: %s; displayName: %s%n", pid, displayName);
                virtualMachine = VirtualMachine.attach(pid);
                System.out.printf("Loading agent to id: %s%n", pid);
                virtualMachine.loadAgent(agentLocation);
            } catch (Exception e){
                System.out.println("An error occurred attaching!");
                e.printStackTrace();
            } finally {
                try {
                    if (virtualMachine != null) {
                        virtualMachine.detach();
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred detaching!");
                    e.printStackTrace();
                }
            }
        });
    }

}
