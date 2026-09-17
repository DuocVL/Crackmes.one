import java.io.*;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.jar.*;

public class ExtractDat {

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: java ExtractDat <input.jar> <output_dir>");
            return;
        }

        Path jarPath = Paths.get(args[0]);
        Path outputDir = Paths.get(args[1]);

        Files.createDirectories(outputDir);

        try (JarFile jar = new JarFile(jarPath.toFile())) {

            Enumeration<JarEntry> entries = jar.entries();

            while (entries.hasMoreElements()) {

                JarEntry entry = entries.nextElement();

                if (entry.isDirectory())
                    continue;

                String name = entry.getName();

                if (!name.endsWith(".dat"))
                    continue;

                // Bỏ ".dat"
                String className =
                        name.substring(0, name.length() - 4);

                // Tạo path output
                Path output =
                        outputDir.resolve(className + ".class");

                Files.createDirectories(output.getParent());

                try (InputStream in = jar.getInputStream(entry)) {

                    byte[] data = in.readAllBytes();

                    Files.write(output, data);

                    System.out.printf(
                            "[+] %s -> %s (%d bytes)%n",
                            name,
                            output,
                            data.length
                    );
                }
            }
        }

        System.out.println("[+] Done.");
    }
}