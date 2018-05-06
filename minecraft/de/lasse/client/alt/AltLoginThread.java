package de.lasse.client.alt;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserType;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.net.Proxy;

public class AltLoginThread implements Runnable {

    private String user, pass;

    public AltLoginThread(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void run() {
        YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication userAuth = (YggdrasilUserAuthentication) authService.createUserAuthentication(Agent.MINECRAFT);
        userAuth.setUsername(user);
        userAuth.setPassword(pass);

        try {
            userAuth.logIn();
            Minecraft.getMinecraft().session = new Session(
                    userAuth.getSelectedProfile().getName(),
                    userAuth.getSelectedProfile().getId().toString(),
                    userAuth.getAuthenticatedToken(),
                    UserType.MOJANG.getName());
            
            System.out.println("Successfully logged in: " + Minecraft.getMinecraft().getSession().getUsername());

        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
