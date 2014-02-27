package me.wtao.utils.su;

public interface OnShellProcessingListener {
	public void onCommandCompleted(int id,  int exitCode);

	public void onCommandOutput(int id, String line);

	public void onCommandTerminated(int id, String reason);
}
