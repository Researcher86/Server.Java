package ownradio.util;

import com.mpatric.mp3agic.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.ID3v23FieldKey;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * Created by Tanat on 015 15.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceUtilTest {

	@Value("classpath:/media/test.mp3")
	private Resource resource;

	@Value("classpath:/media/01 Attack.mp3")
	private Resource resource2;

	@Value("classpath:/media/2f3356f1-3ef4-464b-9545-5d5216e3a425.mp3")
	private Resource resource3;

	@Value("classpath:/media/5e3e59b0-6ad6-484a-b927-85ca5d731477.mp3")
	private Resource resource4;

	@Value("classpath:/media/76ecda27-c853-47ca-9ce8-5e4048d723b5.mp3")
	private Resource resource5;

	@Value("classpath:/media/e84629f6-603e-4187-a07a-df9973ac69bf.mp3")
	private Resource resource6;

	@Test
	public void readMetaDataFromMediaFile() throws Exception {
		readMetaDataFromMediaFile(resource.getFile());
		readMetaDataFromMediaFile(resource2.getFile());
		readMetaDataFromMediaFile(resource3.getFile());
		readMetaDataFromMediaFile(resource4.getFile());
		readMetaDataFromMediaFile(resource5.getFile());
		readMetaDataFromMediaFile(resource6.getFile());
	}

	public void readMetaDataFromMediaFile(File file) throws UnsupportedEncodingException {
		AudioFile f = null;

		// http://www.jthink.net/jaudiotagger/examples_read.jsp

		try {
			f = AudioFileIO.read(file);
		} catch (CannotReadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Tag tag = f.getTag();

		String artist = tag.getFirst(FieldKey.ARTIST);
		String title = tag.getFirst(FieldKey.TITLE);

//		AbstractTagFrameBody body = (((ID3v23Frame) tag.getFirstField(ID3v23FieldKey.ALBUM.getFrameId())).getBody());
//		assertEquals(TextEncoding.ISO_8859_1, body.getTextEncoding());


		artist = new String(artist.getBytes("UTF-8"), "cp1251");
//		title = new String(title.getBytes("ISO-8859-1"), "UTF-8");

//		artist = Charset.forName("UTF-8").encode(artist).toString();


		System.out.println(artist);
		System.out.println(getEncoding(artist));
		System.out.println(title);
		System.out.println(getEncoding(title));
	}

	public String getEncoding(String string) {
		byte[] buf = new byte[4096];
		ByteInputStream byteInputStream = new ByteInputStream(string.getBytes(), string.getBytes().length);

		UniversalDetector detector = new UniversalDetector(null);

		int nread;
		try {
			while ((nread = byteInputStream.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		detector.dataEnd();

		String encoding = detector.getDetectedCharset();
		detector.reset();

		return encoding;
	}

	@Test
	public void name() throws Exception {
		show(resource.getFile());
		show(resource2.getFile());
		show(resource3.getFile());
		show(resource4.getFile());
		show(resource5.getFile());
		show(resource6.getFile());
	}

	private void show(File file) throws Exception {
		Mp3File mp3file = new Mp3File(file);
		if (mp3file.hasId3v1Tag()) {
			ID3v1 id3v1Tag = mp3file.getId3v1Tag();
			System.out.println("Track: " + id3v1Tag.getTrack());
			System.out.println("Artist: " + id3v1Tag.getArtist());
			System.out.println("Title: " + id3v1Tag.getTitle());
			System.out.println("Album: " + id3v1Tag.getAlbum());
			System.out.println("Year: " + id3v1Tag.getYear());
			System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
			System.out.println("Comment: " + id3v1Tag.getComment());
		}

		if (mp3file.hasId3v1Tag()) {
			ID3v2 id3v2 = mp3file.getId3v2Tag();
			System.out.println("Track: " + id3v2.getTrack());
			System.out.println("Artist: " + id3v2.getArtist());
			System.out.println("Title: " + id3v2.getTitle());
			System.out.println("Album: " + id3v2.getAlbum());
			System.out.println("Year: " + id3v2.getYear());
			System.out.println("Genre: " + id3v2.getGenre() + " (" + id3v2.getGenreDescription() + ")");
			System.out.println("Comment: " + id3v2.getComment());
		}
	}
}