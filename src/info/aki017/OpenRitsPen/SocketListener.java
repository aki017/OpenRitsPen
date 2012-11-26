package info.aki017.OpenRitsPen;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener{
	public enum Command {
		USE_PEN {
			public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
				openRitsPen.usePen(Integer.parseInt(arg1),Integer.parseInt(arg2));
			}
		},
		CHANGE_COLOR {
			public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
				openRitsPen.changeColor(Integer.parseInt(arg1));
			}
		},
		CHANGE_COLOR2 {
			public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
				String[] col;
				col = arg1.split(";");
				if (col.length == 3)
				{
					int r = Integer.parseInt(col[0]);
					int g = Integer.parseInt(col[1]);
					int b = Integer.parseInt(col[2]);
					openRitsPen.changeColor(new Color(r, g, b));
				}
			}
		},
		CHANGE_WIDTH {
			public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
				openRitsPen.changeSize(Integer.parseInt(arg1));
			}
		},
		CHANGE_SPEED {
			public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
				openRitsPen.changeSpeed(Integer.parseInt(arg1));
			}
		},
		CREATE_PEN {
			public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
				// TODO
			}
		},
		CHANGE_PEN_TOP {
			public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
				// TODO
			}
		},
		CLOSE_PEN {
			public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
				// TODO
			}
		};

		public void command(OpenRitsPen openRitsPen, String arg1, String arg2) {
			throw new UnsupportedOperationException("There is no implementation yet.");
		}
	}

	private OpenRitsPen openRitsPen;

	public SocketListener(OpenRitsPen openRitsPen) {
		this.openRitsPen = openRitsPen;
	}

	/**
	 * 待ち受けを開始する
	 */
	public void start() {
		try {
			ServerSocket serverSock = new ServerSocket(4242);
			while (true) {
				System.out.println("接続を待っています");
				Socket sock = serverSock.accept();

				InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
				BufferedReader reader = new BufferedReader(streamReader);

				try {
					String receiveLine;
					while ((receiveLine = reader.readLine()) != null) {
						parse(receiveLine);
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("I/O error");
				}

				reader.close();
			}
		} catch (IOException e) {
			System.out.println("error：SocketError");
		}
	}

	/**
	 * パースします
	 * @param str 受信した文字列
	 */
	private void parse(String str){
		String[] args;
		args = str.split(":");

		if (args.length == 3)
		{
			int order = Integer.parseInt(args[2]);

			command(args[0],args[1],order);
		}
	}

	/**
	 * コマンドー
	 * @param arg1 第一引数
	 * @param arg2 第二引数
	 * @param order コマンドー
	 */
	private void command(String arg1,String arg2,int order){
		Command[] values = Command.values();
		if (0 <= order && order < values.length)
		{
			values[order].command(openRitsPen, arg1, arg2);
		}
	}
}
