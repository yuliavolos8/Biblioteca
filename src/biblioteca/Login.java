package biblioteca;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class Login extends JFrame{
	public Login() {
		
		JLabel etiquetaDeInicio = new JLabel("pagina de inicio");
		etiquetaDeInicio.setSize(10, 20);
		etiquetaDeInicio.setLocation(0,0);
		/*etiquetaDeInicio.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(etiquetaDeInicio, BorderLayout.NORTH);*/
		
		JLabel etiquetaDNI = new JLabel("ingrese su DNI");
	//	getContentPane().add(etiquetaDNI, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("Ingrese su contrasenia");
	/*	lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);*/
		etiquetaDeInicio.show();
		etiquetaDNI.show();
		lblNewLabel.show();
		add(etiquetaDeInicio);
		add(lblNewLabel);
		add(etiquetaDNI);
		pack();
		setVisible( true);
		
	}

}
