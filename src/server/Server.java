package server;

import commands.Command;
import commands.CommandExit;
import commands.CommandSave;
import other.App;
import other.Serialization;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import java.util.logging.*;

public class Server {
    private DatagramChannel channel;
    private SocketAddress address;
    private byte[] buffer;
    private Serialization<Command> commandSerialization = new Serialization<>();
    private Serialization<Response> responseSerialization = new Serialization<>();
    public static final Logger log = Logger.getLogger(String.valueOf(Server.class));


    public Server() {
        buffer = new byte[65536];
    }

    /**
     * Method that accepts connections
     * @param port
     * @throws IOException
     */
    public void connect(int port) throws IOException {
        address = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(address);
    }

    /**
     * Method that reads received information
     * and send the answer back
     * @param application
     */
    public void run(App application) {
        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                do {
                    address = channel.receive(byteBuffer);
                } while (address == null);
                Command command = commandSerialization.readObject(buffer);
                System.out.println("The server received the command " + command);
                String result = processCommand(application, command);
                log.info("Server receive command " + command.toString());
                System.out.println("Command " + command + " is executed, send a response to the client...");
                log.info("Command " + command.toString() + " is completed, send an answer to the client");
                Response response = new Response(result);
                byte[] answer = responseSerialization.writeObject(response);
                byteBuffer = ByteBuffer.wrap(answer);
                channel.send(byteBuffer, address);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The server is waiting for a command, and the client is sending something unknown...");
        } catch (IOException e) {
            System.out.println("Problems with connection...");
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.out.println("The server was expecting a command, but got something wrong...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that executes commands
     * @param application
     * @param command
     * @return
     */
    public String processCommand(App application, Command command) {
        if (command instanceof CommandExit) {
            new CommandSave().execute(application);
            log.info("Server receive command " + new CommandSave().toString());
        }
        String result = command.execute(application);
        application.setCollection(command.getCollection());
        application.setIdList(command.getIdList());
        return result;
    }
}
