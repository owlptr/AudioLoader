package audioloader.core;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface AudioLoaderConnector extends Library
{
	AudioLoaderConnector connector = (AudioLoaderConnector)Native.load(
			"audio_loader.so", AudioLoaderConnector.class);
	
	int open_audio(String path);
	int read_double(Pointer result, int bsize);
	SoundfileInfo get_audio_info();
	int close_audio();
}
