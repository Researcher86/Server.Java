package ownradio.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Tanat on 015 15.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceUtilTest {

	@Value("classpath:/media/test.mp3")
	private Resource resource;

	@Test
	public void readMetaDataFromMediaFile() throws Exception {
		ResourceUtil.readMetaDataFromMediaFile(resource.getFile());
	}

}