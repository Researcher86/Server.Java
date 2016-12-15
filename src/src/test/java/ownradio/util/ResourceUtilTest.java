package ownradio.util;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Tanat on 015 15.12.16.
 */
public class ResourceUtilTest {

	@Test
	public void readMetaDataFromMediaFile() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("media/test.mp3").getFile());

		ResourceUtil.readMetaDataFromMediaFile(file);
	}

}