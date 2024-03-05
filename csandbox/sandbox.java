import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Policy;
import java.security.URIParameter;
import java.security.Permissions;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.SecurityPermission;
import java.net.URI;
import java.io.FilePermission;
import java.io.File;

public class sandbox {

    public static void main(String[] args) {
        try {
            // Get Username
            String userName = System.getProperty("user.name");
            // Command
            String sandboxCommand = "bash -c 'cd /home/" + userName + " && your_command_here'";
            // 7Level sandbox
            createSevenLevelSandbox(userName, sandboxCommand);
            grantAccessToApplication("/media/*");
            String encryptedMessage = encryptSHA256("message to encrypt");
            System.out.println("Encrypted message: " + encryptedMessage);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void createSevenLevelSandbox(String userName, String sandboxCommand) throws IOException {
        for (int level = 1; level <= 7; level++) {
            String sandboxDir = "/home/" + userName + "/sandbox_level_" + level;
            String command = "bash -c 'mkdir -p " + sandboxDir + " && cd " + sandboxDir + " && " + sandboxCommand + "'";
            ProcessBuilder pb = new ProcessBuilder("sudo", "-u", userName, command);
            Process p = pb.start();
        }
    }

    private static void grantAccessToApplication(String path) {
        try {
            // 現在のセキュリティポリシーを取得
            Policy currentPolicy = Policy.getPolicy();

            // 新しいパーミッションコレクションを作成
            Permissions permissions = new Permissions();
            permissions.add(new AllPermission()); // ここでは、全てのパーミッションを許可しています。実際には、必要なパーミッションのみを許可するようにしてください。

            // ファイルパーミッションを追加
            permissions.add(new FilePermission(path, "read,write"));

            // 新しいセキュリティポリシーを作成
            Policy newPolicy = new Policy() {
                @Override
                public PermissionCollection getPermissions(CodeSource codesource) {
                    return permissions;
                }

                @Override
                public void refresh() {
                    // 何もしない
                }
            };

            // 新しいセキュリティポリシーを設定
            Policy.setPolicy(newPolicy);

            // セキュリティマネージャに新しいポリシーを設定
            System.setSecurityManager(new SecurityManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String encryptSHA256(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedhash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
