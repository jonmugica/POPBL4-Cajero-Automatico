package lineaserie;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import vistas.Principal;

public class SerialComm {
	InputStream in;
	OutputStream out;
	CommPort commPort;
	Principal principal;
	
	public SerialComm(Principal principal) {
		this.principal = principal;
	}
	public void conectar ( CommPortIdentifier portIdentifier) throws Exception
    {
        if ( portIdentifier.isCurrentlyOwned() ){
            System.out.println("Error puerta en uso");
        }else {
             commPort = portIdentifier.open("Mi programa",2000);
            
            if ( commPort instanceof SerialPort ) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();
            }else {
                System.out.println("Error este programa solo funciona con linea serie");
            }
        }     
    }
    
    /**
     * @throws IOException  
     * @throws SQLException 
     * @throws ClassNotFoundException */
    
    public void  leer () throws IOException, ClassNotFoundException, SQLException
    {
    	String accion;
        byte[] buffer = new byte[1024];
        int len = -1;
       
        while ( ( len = this.in.read(buffer)) > -1 ){
           
        	accion = new String(buffer,0,len);
         
        	switch (accion) {
        	
        	case "C":
        		System.out.println("CANCELANDO COMPRA");
        		principal.getVentanaPrincipal().getContentPane().removeAll();
    			principal.getVentanaPrincipal().getContentPane().repaint();
    			principal.cambiarPanelAcceso();
        		break;
        		
        	case "A":
        		System.out.println("ACEPTANDO COMPRA");
        		principal.getPanelPrincipal().almacenarProductos();
        		principal.getVentanaPrincipal().getContentPane().removeAll();
    			principal.getVentanaPrincipal().getContentPane().repaint();
    			principal.cambiarPanelAcceso();
        		break;
        	}
        
            }
            
        }    
     
    

    /** */
    public void escribir  (String msg)
    {
        try
        {                
            this.out.write(msg.getBytes());              
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }            
    }
    public CommPortIdentifier encontrarPuerto()
    {
        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while ( portEnum.hasMoreElements() ) 
        {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            if (this.getPortTypeName(portIdentifier.getPortType()).equals("Serial")) {
            	return portIdentifier;
            }
            System.out.println(portIdentifier.getName()  +  " - " +  getPortTypeName(portIdentifier.getPortType()) );
        } 
        return null;
    }
    
    public String getPortTypeName ( int portType )
    {
        switch ( portType )
        {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }
    public void cerrar() {
    	commPort.close();
    }
}
