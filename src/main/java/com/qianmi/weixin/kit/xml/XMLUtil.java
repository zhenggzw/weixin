package com.qianmi.weixin.kit.xml;

import com.qianmi.weixin.bean.back.WXPayResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.Writer;

/**
 * author: Tkk
 * date: 2015/8/7
 */
public class XMLUtil {

    public static <T> T toBean(String xmlContent, String alias, Class<T> beanClass) {
        XStream xstream = toXStream();
        xstream.alias("xml", beanClass);
        return (T) xstream.fromXML(xmlContent);
    }

    public static XStream toXStream() {
        XStream xstream = new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out, getNameCoder()) {
                    protected String PREFIX_CDATA = "<![CDATA[";
                    protected String SUFFIX_CDATA = "]]>";
                    protected String PREFIX_MEDIA_ID = "<MediaId>";
                    protected String SUFFIX_MEDIA_ID = "</MediaId>";

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
                            writer.write(text);
                        } else if (text.startsWith(PREFIX_MEDIA_ID) && text.endsWith(SUFFIX_MEDIA_ID)) {
                            writer.write(text);
                        } else {
                            super.writeText(writer, text);
                        }

                    }
                };
            }
        });
        xstream.ignoreUnknownElements();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        return xstream;
    }
}
