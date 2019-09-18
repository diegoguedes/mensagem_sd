package calculadora;

import java.net.*;
import java.util.ArrayList;

import calculadora.Mensagem.TipoOperacao;

import java.io.*;
import java.math.BigDecimal;

public class Cliente {

	public static void main(String args[]) {
		// arguments supply message and hostname of destination
		Socket s = null;
		Mensagem msg;
		ArrayList<BigDecimal> numeros = new ArrayList<>();
		numeros.add(new BigDecimal(2));
		numeros.add(new BigDecimal(2));

		try {
			int serverPort = 8989;
			s = new Socket("localhost", serverPort);
			s.setSoTimeout(3000);
			s.setKeepAlive(true);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			Mensagem recebido;
			try {
				// SOMA
				msg = new Mensagem(TipoOperacao.SOMA, numeros);
				out.writeObject(msg);
				recebido = (Mensagem) in.readObject();
				System.out.println("Received: " + recebido.getNumeros().get(0).toString());
				// out.reset();

				// MULT
				msg = new Mensagem(TipoOperacao.MULTIPLICACAO, numeros);
				// out = new ObjectOutputStream(out);
				out.writeObject(msg);
				recebido = (Mensagem) in.readObject();
				System.out.println("Received: " + recebido.getNumeros().get(0).toString());

				// DIV
				msg = new Mensagem(TipoOperacao.DIVISAO, numeros);
				out.writeObject(msg);
				recebido = (Mensagem) in.readObject();
				System.out.println("Received: " + recebido.getNumeros().get(0).toString());

				// SUB
				msg = new Mensagem(TipoOperacao.SUBTRACAO, numeros);
				out.writeObject(msg);
				recebido = (Mensagem) in.readObject();
				System.out.println("Received: " + recebido.getNumeros().get(0).toString());
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Resposta n�o enviada");
			}
		} catch (UnknownHostException e) {
			System.out.println("Sock:" + e.getMessage());
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
	
}
