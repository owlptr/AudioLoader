package core;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Libsndfile extends Library {
	public AudioLoaderConnector conn = AudioLoaderConnector.connector;
	Libsndfile ndfile = (Libsndfile)Native.load("libsndfile.so", Libsndfile.class);
}
