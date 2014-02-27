package me.wtao.utils.su;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.exceptions.RootDeniedException;
import com.stericson.RootTools.execution.Command;
import com.stericson.RootTools.execution.CommandCapture;

final class RootToolsImpl implements RootComp {

	@Override
	public boolean isBusyboxAvailable() {
		return RootTools.isBusyboxAvailable();
	}

	@Override
	public boolean isRootAvailable() {
		return RootTools.isRootAvailable();
	}

	@Override
	public boolean isAccessGiven() {
		return RootTools.isAccessGiven();
	}

	@Override
	public boolean sudo(String... command) {
		CommandCapture cmdCap = new CommandCapture(0, command);

		try {
			RootTools.getShell(true).add(cmdCap);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (RootDeniedException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean sudo(final OnShellProcessingListener listener, String... command) {
		Command cmd = new Command(0, command) {
			
			@Override
			public void commandTerminated(int id, String reason) {
				listener.onCommandTerminated(id, reason);
			}
			
			@Override
			public void commandOutput(int id, String line) {
				listener.onCommandOutput(id, line);
			}
			
			@Override
			public void commandCompleted(int id, int exitCode) {
				listener.onCommandCompleted(id, exitCode);
			}
		};

		try {
			RootTools.getShell(true).add(cmd);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (RootDeniedException e) {
			e.printStackTrace();
		}

		return false;
	}

}
