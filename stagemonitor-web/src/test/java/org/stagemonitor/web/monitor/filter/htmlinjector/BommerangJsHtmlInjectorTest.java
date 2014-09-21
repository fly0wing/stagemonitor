package org.stagemonitor.web.monitor.filter.htmlinjector;

import org.junit.Test;
import org.stagemonitor.core.util.IOUtils;
import org.stagemonitor.web.monitor.rum.BommerangJsHtmlInjector;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BommerangJsHtmlInjectorTest {

	@Test
	public void testBommerangJsExistsAndHashIsCorrect() throws Exception {
		final String location = "/META-INF/resources/stagemonitor/static/rum/" + BommerangJsHtmlInjector.BOOMERANG_FILENAME;
		final InputStream inputStream = getClass().getResourceAsStream(location);
		assertNotNull(inputStream);

		String contentHash = toSHA1(IOUtils.toString(inputStream).getBytes()).substring(0, 11);
		assertEquals("boomerang-" + contentHash + ".min.js", BommerangJsHtmlInjector.BOOMERANG_FILENAME);
	}

	public static String toSHA1(byte[] convertme) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		return byteArrayToHexString(md.digest(convertme));
	}

	public static String byteArrayToHexString(byte[] bytes) {
		String result = "";
		for (byte s : bytes) {
			result += Integer.toString((s & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
}