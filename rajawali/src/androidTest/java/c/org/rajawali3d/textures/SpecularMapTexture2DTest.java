package c.org.rajawali3d.textures;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.opengl.GLES20;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import c.org.rajawali3d.textures.annotation.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rajawali3d.R;

import java.nio.ByteBuffer;

/**
 * @author Jared Woolston (Jared.Woolston@gmail.com)
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class SpecularMapTexture2DTest {

    @Test
    public void testConstructorOtherTexture() throws Exception {
        final SpecularMapTexture2D other = new SpecularMapTexture2D("TEST", getContext(), R.drawable.earth_diffuse);
        final SpecularMapTexture2D texture = new SpecularMapTexture2D(other);
        assertNotNull(texture);
        assertEquals("TEST", texture.getTextureName());
        assertEquals(Type.SPECULAR, texture.getTextureType());
        assertEquals(other.getTextureData(), texture.getTextureData());
    }

    @Test
    public void testConstructorName() throws Exception {
        final SpecularMapTexture2D texture = new SpecularMapTexture2D("TEST");
        assertNotNull(texture);
        assertEquals("TEST", texture.getTextureName());
        assertEquals(Type.SPECULAR, texture.getTextureType());
        assertNull(texture.getTextureData());
    }

    @Test
    public void testConstructorNameResourceId() throws Exception {
        final SpecularMapTexture2D texture = new SpecularMapTexture2D("TEST", getContext(), R.drawable.earth_diffuse);
        assertNotNull(texture);
        assertEquals("TEST", texture.getTextureName());
        assertEquals(Type.SPECULAR, texture.getTextureType());
        assertNotNull(texture.getTextureData());
        assertTrue(texture.getTextureData().hasBitmap());
    }

    @Test
    public void testConstructorNameDataReference() throws Exception {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(4 * 256 * 256);
        final TextureDataReference reference = new TextureDataReference(null, buffer, GLES20.GL_RGBA,
                                                                        GLES20.GL_UNSIGNED_BYTE, 256, 256);
        final SpecularMapTexture2D texture = new SpecularMapTexture2D("TEST", reference);
        assertNotNull(texture);
        assertEquals("TEST", texture.getTextureName());
        assertEquals(Type.SPECULAR, texture.getTextureType());
        assertEquals(reference, texture.getTextureData());
    }

    @Test
    public void testClone() throws Exception {
        final SpecularMapTexture2D other = new SpecularMapTexture2D("TEST", getContext(), R.drawable.earth_diffuse);
        final SpecularMapTexture2D texture = other.clone();
        assertNotNull(texture);
        assertEquals("TEST", texture.getTextureName());
        assertEquals(Type.SPECULAR, texture.getTextureType());
        assertEquals(other.getTextureData(), texture.getTextureData());
    }

    @Test()
    public void testCloneAndThrow() throws Exception {
        final SpecularMapTexture2D other = new SpecularMapTexture2D("TEST");
        assertNull(other.clone());
    }
}