package hoge.mock2.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import hoge.mock2.common.Log;
import hoge.mock2.common.Result;

public class View {

	protected Shell shell;
	private Text OutputText;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			View window = new View();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Log.sysout("画面が表示されました");
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				OutputText.setText(Result.getData());
				shell.redraw();
				display.sleep();
			}
		}
	}

	public void close() {
		Log.sysout("画面をクローズします");
		shell.dispose();
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");

		OutputText = new Text(shell, SWT.BORDER);
		OutputText.setBounds(117, 10, 198, 73);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Log.sysout("中断ボタンが押されました");
				Result.setResult("2");
			}
		});
		btnNewButton.setBounds(122, 117, 198, 81);
		btnNewButton.setText("中断");

	}
}
