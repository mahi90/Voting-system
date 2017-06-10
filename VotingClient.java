/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingclient;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import votinginterface.IVoting;

/**
 *
 * @author mahi
 */
public class VotingClient {

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // TODO code application logic here
        try 
        {
            Registry r = LocateRegistry.getRegistry("127.0.0.1", 1099);
            Login.svi = (IVoting) r.lookup("mahivoting");
            (new Login()).setVisible(true);
            //System.out.println("start");
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    
}
