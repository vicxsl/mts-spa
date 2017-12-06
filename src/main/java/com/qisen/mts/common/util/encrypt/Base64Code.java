package com.qisen.mts.common.util.encrypt;


public class Base64Code
{

    private Base64Code()
    {
    }

    public static final byte[] decode(byte abyte0[])
    {
        if(abyte0 == null)
            return null;
        int i = abyte0.length;
        if(i < 1)
            return null;
        for(; abyte0[i - 1] == 61; i--);
        byte abyte1[] = new byte[i - abyte0.length / 4];
        for(int j = 0; j < abyte0.length; j++)
            abyte0[j] = _decode_map[abyte0[j]];

        int i1 = abyte1.length - 2;
        int k = 0;
        int l;
        for(l = 0; l < i1; l += 3)
        {
            abyte1[l] = (byte)(abyte0[k] << 2 & 0xff | abyte0[k + 1] >>> 4 & 0x3);
            abyte1[l + 1] = (byte)(abyte0[k + 1] << 4 & 0xff | abyte0[k + 2] >>> 2 & 0xf);
            abyte1[l + 2] = (byte)(abyte0[k + 2] << 6 & 0xff | abyte0[k + 3] & 0x3f);
            k += 4;
        }

        if(l < abyte1.length)
            abyte1[l] = (byte)(abyte0[k] << 2 & 0xff | abyte0[k + 1] >>> 4 & 0x3);
        if(++l < abyte1.length)
            abyte1[l] = (byte)(abyte0[k + 1] << 4 & 0xff | abyte0[k + 2] >>> 2 & 0xf);
        return abyte1;
    }

    public static final byte[] encode(byte abyte0[])
    {
        if(abyte0 == null)
            return null;
        byte abyte1[] = new byte[((abyte0.length + 2) / 3) * 4];
        int k = abyte0.length - 2;
        int i = 0;
        int j = 0;
        for(; i < k; i += 3)
        {
            abyte1[j++] = _encode_map[abyte0[i] >>> 2 & 0x3f];
            abyte1[j++] = _encode_map[abyte0[i + 1] >>> 4 & 0xf | abyte0[i] << 4 & 0x3f];
            abyte1[j++] = _encode_map[abyte0[i + 2] >>> 6 & 0x3 | abyte0[i + 1] << 2 & 0x3f];
            abyte1[j++] = _encode_map[abyte0[i + 2] & 0x3f];
        }

        if(i < abyte0.length)
        {
            abyte1[j++] = _encode_map[abyte0[i] >>> 2 & 0x3f];
            if(i < abyte0.length - 1)
            {
                abyte1[j++] = _encode_map[abyte0[i + 1] >>> 4 & 0xf | abyte0[i] << 4 & 0x3f];
                abyte1[j++] = _encode_map[abyte0[i + 1] << 2 & 0x3f];
            } else
            {
                abyte1[j++] = _encode_map[abyte0[i] << 4 & 0x3f];
            }
        }
        for(; j < abyte1.length; j++)
            abyte1[j] = 61;

        return abyte1;
    }

    protected static byte _encode_map[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 43, 47
    };
    protected static byte _decode_map[];

    static 
    {
        _decode_map = new byte[128];
        for(int i = 0; i < _encode_map.length; i++)
            _decode_map[_encode_map[i]] = (byte)i;

    }
}
