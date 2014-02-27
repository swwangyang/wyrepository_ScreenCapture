package me.wtao.utils.su;

public final class RootManager {
	private static RootComp sRootTools = null;

	public RootManager() {
		if (sRootTools == null) {
			synchronized (RootManager.class) {
				if (sRootTools == null) {
					sRootTools = new RootToolsImpl();
				}
			}
		}
	}

	public boolean isBusyboxAvailable() {
		return sRootTools.isBusyboxAvailable();
	}

	public boolean isRootAvailable() {
		return sRootTools.isRootAvailable();
	}

	public boolean isAccessGiven() {
		return sRootTools.isAccessGiven();
	}

	public boolean sudo(String... command) {
		return sRootTools.sudo(command);
	}

	public boolean sudo(OnShellProcessingListener listener, String... command) {
		return sRootTools.sudo(listener, command);
	}

}
