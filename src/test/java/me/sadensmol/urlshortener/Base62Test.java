package me.sadensmol.urlshortener;

import io.seruco.encoding.base62.Base62;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

public class Base62Test {
    @Test
    public void encodeAndDecodeTest()    {
        long input = 12345;
        Base62 base62 = Base62.createInstance();
        String encodedString = new String(base62.encode(String.valueOf(input).getBytes()));
        String decodedString = new String(base62.decode(encodedString.getBytes()));
        Assert.assertTrue(decodedString.equals(String.valueOf(input)));
    }

    @Test
    public void encodeAndDecodeZeroTest()    {
        long input = 0;
        Base62 base62 = Base62.createInstance();
        String encodedString = new String(base62.encode(String.valueOf(input).getBytes()));
        String decodedString = new String(base62.decode(encodedString.getBytes()));
        Assert.assertTrue(decodedString.equals(String.valueOf(input)));
    }

    @Test
    public void encodeAndDecodeMaxTest()    {
        long input = Long.MAX_VALUE;
        Base62 base62 = Base62.createInstance();
        String encodedString = new String(base62.encode(String.valueOf(input).getBytes()));
        String decodedString = new String(base62.decode(encodedString.getBytes()));
        Assert.assertTrue(decodedString.equals(String.valueOf(input)));
    }

}
