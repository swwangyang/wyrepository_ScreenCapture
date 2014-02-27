package me.wtao.utils.su;

interface RootComp {
	public boolean isBusyboxAvailable();
	public boolean isRootAvailable();
	public boolean isAccessGiven();
	public boolean sudo(String... command);
	public boolean sudo(final OnShellProcessingListener listener, String... command);
}
