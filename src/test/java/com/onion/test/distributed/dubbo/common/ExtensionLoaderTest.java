package com.onion.test.distributed.dubbo.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import com.alibaba.dubbo.common.compiler.Compiler;
import com.alibaba.dubbo.remoting.Transporter;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Protocol;

public class ExtensionLoaderTest {

    @Test
    public void test() {

        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol dubbo = extensionLoader.getExtension("dubbo");
        System.out.println(dubbo);

        Set<String> loadedExtensions = ExtensionLoader.getExtensionLoader(Filter.class).getLoadedExtensions();
        Set<String> supportedExtensions = ExtensionLoader.getExtensionLoader(Filter.class).getSupportedExtensions();

        System.out.println(loadedExtensions);
        System.out.println(supportedExtensions);

        Filter executelimit = ExtensionLoader.getExtensionLoader(Filter.class).getExtension("executelimit");
        System.out.println(executelimit);
    }

    @Test
    public void testCode() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Method createAdaptiveExtensionClassCode = ExtensionLoader.class.getDeclaredMethod("createAdaptiveExtensionClassCode");
        createAdaptiveExtensionClassCode.setAccessible(true);
        Object invoke = createAdaptiveExtensionClassCode.invoke(extensionLoader);
        System.out.println(invoke);

        ExtensionLoader<Transporter> extensionLoader2 = ExtensionLoader.getExtensionLoader(Transporter.class);
        Method createAdaptiveExtensionClassCode2 = ExtensionLoader.class.getDeclaredMethod("createAdaptiveExtensionClassCode");
        createAdaptiveExtensionClassCode2.setAccessible(true);
        Object invoke2 = createAdaptiveExtensionClassCode2.invoke(extensionLoader2);
        System.out.println(invoke2);

        /*

            package com.alibaba.dubbo.rpc;
            import com.alibaba.dubbo.common.extension.ExtensionLoader;

            public class Protocol$Adpative implements com.alibaba.dubbo.rpc.Protocol {
                public void destroy() {
                    throw new UnsupportedOperationException("method public abstract void com.alibaba.dubbo.rpc.Protocol.destroy() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!");
                }
                public int getDefaultPort() {
                    throw new UnsupportedOperationException("method public abstract int com.alibaba.dubbo.rpc.Protocol.getDefaultPort() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!");
                }
                public com.alibaba.dubbo.rpc.Exporter export(com.alibaba.dubbo.rpc.Invoker arg0) throws com.alibaba.dubbo.rpc.RpcException {
                    if (arg0 == null)
                        throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument == null");
                    if (arg0.getUrl() == null)
                        throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument getUrl() == null");
                    com.alibaba.dubbo.common.URL url = arg0.getUrl();
                    String extName = url.getProtocol();
                    if(extName == null)
                        throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(" + url.toString() + ") use keys([protocol])");

                    com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);
                    return extension.export(arg0);
                }
                public com.alibaba.dubbo.rpc.Invoker refer(java.lang.Class arg0, com.alibaba.dubbo.common.URL arg1) throws com.alibaba.dubbo.rpc.RpcException {
                    if (arg1 == null) throw new IllegalArgumentException("url == null");
                    com.alibaba.dubbo.common.URL url = arg1;
                    String extName = url.getProtocol();
                    if(extName == null) throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(" + url.toString() + ") use keys([protocol])");

                    com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);
                    return extension.refer(arg0, arg1);
                }
            }

            package com.alibaba.dubbo.remoting;
            import com.alibaba.dubbo.common.extension.ExtensionLoader;

            public class Transporter$Adpative implements com.alibaba.dubbo.remoting.Transporter {
                public com.alibaba.dubbo.remoting.Client connect(com.alibaba.dubbo.common.URL arg0, com.alibaba.dubbo.remoting.ChannelHandler arg1) throws com.alibaba.dubbo.remoting.RemotingException {
                    if (arg0 == null)
                        throw new IllegalArgumentException("url == null");

                    com.alibaba.dubbo.common.URL url = arg0;
                    String extName = url.getParameter("client", url.getParameter("transporter"));
                    if(extName == null)
                        throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.remoting.Transporter) name from url(" + url.toString() + ") use keys([client, transporter])");

                    com.alibaba.dubbo.remoting.Transporter extension = (com.alibaba.dubbo.remoting.Transporter)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.remoting.Transporter.class).getExtension(extName);
                    return extension.connect(arg0, arg1);
                }

                public com.alibaba.dubbo.remoting.Server bind(com.alibaba.dubbo.common.URL arg0, com.alibaba.dubbo.remoting.ChannelHandler arg1) throws com.alibaba.dubbo.remoting.RemotingException {
                    if (arg0 == null)
                        throw new IllegalArgumentException("url == null");

                    com.alibaba.dubbo.common.URL url = arg0;
                    String extName = url.getParameter("server", url.getParameter("transporter"));
                    if(extName == null)
                        throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.remoting.Transporter) name from url(" + url.toString() + ") use keys([server, transporter])");

                    com.alibaba.dubbo.remoting.Transporter extension = (com.alibaba.dubbo.remoting.Transporter)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.remoting.Transporter.class).getExtension(extName);
                    return extension.bind(arg0, arg1);
                }
            }

         */
    }

    @Test
    public void testCompile() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader = ExtensionLoader.class.getClassLoader();

        Compiler compiler = ExtensionLoader.getExtensionLoader(Compiler.class).getAdaptiveExtension();
        Class<?> compile = compiler.compile(getCode(), classLoader);

        System.out.println(compile);

        Class<?> helloWorld = compiler.compile("public class HelloWorld {\n"
                                               + "    public void say() {\n"
                                               + "        System.out.println(\"hello world\");\n"
                                               + "    }\n"
                                               + "}", classLoader);
        Object obj = helloWorld.newInstance();
        Method say = helloWorld.getDeclaredMethod("say");
        Object invoke = say.invoke(obj);

    }

    private String getCode() {
        return "package com.alibaba.dubbo.remoting;\n"
               + "            import com.alibaba.dubbo.common.extension.ExtensionLoader;\n"
               + "\n"
               + "            public class Transporter$Adpative implements com.alibaba.dubbo.remoting.Transporter {\n"
               + "                public com.alibaba.dubbo.remoting.Client connect(com.alibaba.dubbo.common.URL arg0, com.alibaba.dubbo.remoting.ChannelHandler arg1) throws com.alibaba.dubbo.remoting.RemotingException {\n"
               + "                    if (arg0 == null)\n"
               + "                        throw new IllegalArgumentException(\"url == null\");\n"
               + "\n"
               + "                    com.alibaba.dubbo.common.URL url = arg0;\n"
               + "                    String extName = url.getParameter(\"client\", url.getParameter(\"transporter\"));\n"
               + "                    if(extName == null)\n"
               + "                        throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.remoting.Transporter) name from url(\" + url.toString() + \") use keys([client, transporter])\");\n"
               + "\n"
               + "                    com.alibaba.dubbo.remoting.Transporter extension = (com.alibaba.dubbo.remoting.Transporter)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.remoting.Transporter.class).getExtension(extName);\n"
               + "                    return extension.connect(arg0, arg1);\n"
               + "                }\n"
               + "\n"
               + "                public com.alibaba.dubbo.remoting.Server bind(com.alibaba.dubbo.common.URL arg0, com.alibaba.dubbo.remoting.ChannelHandler arg1) throws com.alibaba.dubbo.remoting.RemotingException {\n"
               + "                    if (arg0 == null)\n"
               + "                        throw new IllegalArgumentException(\"url == null\");\n"
               + "\n"
               + "                    com.alibaba.dubbo.common.URL url = arg0;\n"
               + "                    String extName = url.getParameter(\"server\", url.getParameter(\"transporter\"));\n"
               + "                    if(extName == null)\n"
               + "                        throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.remoting.Transporter) name from url(\" + url.toString() + \") use keys([server, transporter])\");\n"
               + "\n"
               + "                    com.alibaba.dubbo.remoting.Transporter extension = (com.alibaba.dubbo.remoting.Transporter)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.remoting.Transporter.class).getExtension(extName);\n"
               + "                    return extension.bind(arg0, arg1);\n"
               + "                }\n"
               + "            }";
    }

}
