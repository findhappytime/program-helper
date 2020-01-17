package fun.findhappytime.utils.encode;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Demo {


//	public static final String ras_publickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArAP9l8csftgxCC8DTYQ3OK1gEB/PIUwpEOgJAWH2VMc9Ji2do7/WHe2AN1hKpCzFxuk/pLPc9EarniE9A0vjES+q4JafVNwarzMPaWO2iIpKS4bwtmuqXnVMSQEGjaaBZksMM32kZETIFQ5ys1HipGyDXIqBFi0OTVylgjaWSSFM7P18q/ww7l8lnPUzOKYtHycMkV5YpvF1uoZZuTUlHs6pHBfrcv0TexNKQoDvGEw5Ld6q8/xVOTYnsXbq0kLnrhQjNbqoAjs1Vd8vkFDvakjfWQnCoejPnoEG0LAWTCl1JU+mJb0mhAjdLwr8jKcdRHlAzKpgBYn7Xzd+ZNct4wIDAQAB";

    //测试
//	public static final String ras_publickey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnoudflpPaXV85idbLEWwMfMW1DnVLToNviRF6/HyLhOo39vsYqt1Zfh2X9iLtan5n4rng8GJnSNTIp9AG+4v1g6/PCq8PXyzNdCHd+4coKg6Dxgt3YXLW58iBmyZNHnEpAYZh0NEN+9o+DCSyHv4d9E0DJ//QmCWLgU1sQJiY9kkNksSEIN2radjbGiV7jcGuH/1eo/qaEOPFxdA82cHYkdLSx8KJAWhHBzZl1rK+tYjIlYfpnzySFLZ6BTaNqBzSNy1v3mRcIqwRmxJn4LQDUzDbEUkelXhH/jGkGoyx4Cu+H+0F4hJaZjZNUZz1rTJGxOzkxfPBc8Uv3fE4PilXQIDAQAB";

//	public static final String rsa_privatekey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCsA/2Xxyx+2DEILwNNhDc4rWAQH88hTCkQ6AkBYfZUxz0mLZ2jv9Yd7YA3WEqkLMXG6T+ks9z0RqueIT0DS+MRL6rglp9U3BqvMw9pY7aIikpLhvC2a6pedUxJAQaNpoFmSwwzfaRkRMgVDnKzUeKkbINcioEWLQ5NXKWCNpZJIUzs/Xyr/DDuXyWc9TM4pi0fJwyRXlim8XW6hlm5NSUezqkcF+ty/RN7E0pCgO8YTDkt3qrz/FU5NiexdurSQueuFCM1uqgCOzVV3y+QUO9qSN9ZCcKh6M+egQbQsBZMKXUlT6YlvSaECN0vCvyMpx1EeUDMqmAFiftfN35k1y3jAgMBAAECggEAORkSAmGQuYIVtHu2gvKSSfPy4SdN3+Wh9ASxiI318Go6OAQIQaSZ9FEzsCtUi255L++YupkhjMwwh0rNL9zuwHOsKuSUetyc1u8B/J+mpWgSBUQDfun6IWSaL9lxTW+poETlBep8uPPD3olvNWrG6oFCkWNAOhY2myNXpQNtm1QxDCC1vfxLLHMew/PglO/2pjgGKXIigp5flgKGVNbjHFoeUXEQj22LvTuJLFlG3eNrTOubKDd68q+sl+D0S14yyPBBd6mEsONqpg7hQlABt663rbZxJT3qluDxsSGe7+f0adLtDJxrbqV0VjO1XhruWqJ3dow1OnVq9q/5vxOOQQKBgQDYrrIiPmEqGsSSlQftgETiuULxY5qrPewoEma6CwzJkPlMlwEhIxtFJuyT4cORZdBD9THk6b5s0EizKGLrrffcMPcu6JzcOQvhOn9Ve4wNujxjtxjtFHnxFlAumvHV/lCnIkTaP91PW0BdJwmnNjzPW4+iD3B6odAzZLVDVw+pUQKBgQDLOm/z/eFWmsLafzMAwB3vjBr8xtCZX1BDRdweZ7NKZ8Lsxs77cv8SJmfs9YE9sr1fq5cMjj9IYydoucd6uZle3PBlZGfD5YVVjH5WwqMthiZVN4iVZbcsbS1UvMzdvcXs2LV382ns0sXILD/9sSqKSNXTQmVqpFkCInAmZoSW8wKBgE8F3ydH8q2lV31R5rNCLV02TWi1d6nuCltO4rIE56P3QEQjaiEIqaBdorgIBQfYxsyR9qFSccVDbNAR8YmcR6HBJdSwtsxF7cQ1Tk0BosE6411GYEvORO6cwZ9NmNb/SbC6ebvSwMZ5NA8E9qtJx4GkFcz3roC61LB5kPW/IZIRAoGBAJ4Kesdelp83yPIL2wjpXh5McSxEHDI5efxm1m/aqwXgvShOzuWiHxPpRAZ1QSVO25ALq3J7KGFNoncBikw5CzlHTbc7pqoj0lWd5R3pTOZ5HVRF1HSB3jmxSYBNNm/2RXYTbMO0Spp9qospRjYuUWuqioY4aw/McN+FEnv6W3/bAoGABZHkrVm67jSQMzUzu0JHZXSO5LoAoSJtiJm0OyCOVFzsx9QFfZtBBmmouKtl5kTPXK+lNUdA5HN46IHNarEKZnBICqyy4yNnNfxuKNdpje2oC309N9qs0d8KNKUyxpVlkAAeyE+XaOfIKzEWYjzJYmFvwRCjNNgT2NX/dWKUL30=";

    //线上
    public static final String ras_publickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAguZUuAuwku4SswK1yZ5gfqx7WQGFOMdBkNzn7xSwRuhlpwTXk+i8KZMC9SUGHFrMVLSYwFMBvSFJMOrJO9DJT+KxJ1wOvLRkOJr3yQNqjiI9GG+n45OPxq8eE1mpJ1HvoFsi1XaRToJwfKRnRFWCU0tCWrB9c7s+SlRDwBh8rFzqIhb1IsRiQkaxN8IozrZsWg+WyGobwQpeN0fuptRtl2rD8A891tPSjPm9PRkkOLvJKXLQLDF6kSqDZOCgGb05FKvwLlrNAjkSsjDryS+7gsnVIi23ldQhXEncxj6+nVyvezeFXfijLyaJkwV9Yu9SBpXc/mE6XM+IDQ/Vi+y/EQIDAQAB";
    public static final String rsa_privatekey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCC5lS4C7CS7hKzArXJnmB+rHtZAYU4x0GQ3OfvFLBG6GWnBNeT6LwpkwL1JQYcWsxUtJjAUwG9IUkw6sk70MlP4rEnXA68tGQ4mvfJA2qOIj0Yb6fjk4/Grx4TWaknUe+gWyLVdpFOgnB8pGdEVYJTS0JasH1zuz5KVEPAGHysXOoiFvUixGJCRrE3wijOtmxaD5bIahvBCl43R+6m1G2XasPwDz3W09KM+b09GSQ4u8kpctAsMXqRKoNk4KAZvTkUq/AuWs0CORKyMOvJL7uCydUiLbeV1CFcSdzGPr6dXK97N4Vd+KMvJomTBX1i71IGldz+YTpcz4gND9WL7L8RAgMBAAECggEACTWxcj2IT94mPKgNKEFXYrI5/3om6GbEiy+vtdeLy4FgXY7up/5w9q/cPmk7c9VDmRYXxThR3/+uiZ2LSQUYlOFLO4yzkL1Ua9MjaeYuog9wov6AHnfsXrtFb3EYgKa4KF0hJ0mvUYnF5k8e4OTvRXL/GShfuzvKVyp0ldhjNTE+bhSuMqLWBav7f78DGb7Q568kyrqExQZzpd2hBj3ouVlCF50KN5TY7UPLq/OJ3Ctet/Y6FY+b/5P3GA4+OKjHoFNdl2o16MZ/ifs14LkFizX7d5gbO7eMH88v7GMiOfn25nemv0osZKvCqgsOBcney96Nc64lvwPuwtMZMt//HQKBgQDNTqJ27JnReAg66hvnzZjq7XGoLKYp527PsydgOy5aGyBaJ6NUU6jYXtMDtgb93D4FhC1/gA4/w4uYo6KdQLvdqhpy0E3f8N0JvBc2QqRecelFWN+JoPcKp2oYBr5pXrHgkS5/JdwH9fJZECvoXSLuufkJwvFbtKPG5Rj+8bA0ewKBgQCjOHBQR4q+goM9vn30kU3FdpuS5cFrL22pCWn1hvfNdhLQVZmdMbeinrRbtsAaRjcYILGFGDIFt1wIyimCU165kkPg0JkYNQkuvlE8fziuKrhT9b6dq94nAmrcCMikBU4ZAjYJ4wFAvEbTThEK1/H/YPgGkx/XtTxeAw02GOvC4wKBgAUnPSK5C59bYPGBoNOvsG/tJ9Yt0LnqijiCabGbqq0uUyCQMCRUuT89GhTUw9YpahEfYTj7EOB+2wkoylMiJdUJiQJqwhml5RZydy6we4m3WECaW3oL5UeKLjqOKMdiThqx6jXc0GoOvVeI9WEuvUq6tkqNbxt7LEU4yOjVTjPzAoGAQ8MCndGVwFHeupuDHy3T487B99J64kzmce7+lnhaGGqUlPl0tNYXLuREbG8Kgd30sYehK2hIHCdrhhqn8Ps7sD8Q/xya8nnSPh21DDClD2mc7P5xDHR13iwawQ7EfZPWQHuyg5hCxOVlYgRe/ul4BlXzoociCSOs1DrgGT1vY5ECgYAUwpLnMOiEiteXxb8kkovDVcyPAjQCOm/NZ5pMXCTGQxLbonnhNU6VU5vYOBtWFoaVu1HojHSI9blgsCRyRjypxLjihEvavYO4v6yHb6yf6OoNyCtzoVKFxvUyTeol69CCX6MNfWWt7B6O/3XphpM+darO9ARihFmmuGEoJTv7Tg==";


    public static final String aes_key = "bG/n4dbtqRJfyslRgayXkA==";

    public static void main(String[] args) throws Exception {
        // 1.生成AES密钥
        // 平安和量化派各自生成密钥，并发送给对方做解密使用
//		genAesKey();

        // 2.生成RAS密钥对
        // 平安和量化派各自生成一对密钥。私钥自己持有，公钥发给合作方持有。私钥用于加签，公钥用于验签
//		genRsaKeyPair();

        // 3.用第1步生成的密钥对做AES加密解密
//		encryptAndDecrypt(aes_key);

        // 4.用第2步生成的密钥对做加签验签
//		signAndVerify(ras_publickey, rsa_privatekey);

        // 5.平安发送请求给量化派，平安将数据做加密加签，量化派对数据做验签解密
        test();

    }


    private static void test() throws Exception {
        // 时间戳
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

        String phoneNo = "13655556666";
        String registerFrom = "433";
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("phoneNo", phoneNo);
        requestMap.put("registerFrom", registerFrom);
        requestMap.put("time", timestamp);
        System.out.println("请求数据Map requestMap：");
        System.out.println(requestMap);
        String requestJson = JSON.toJSONString(requestMap);
        System.out.println("请求数据json requestJson：");
        System.out.println(requestJson);//请求数据以JSON格式传递

        // 加密
        String content = AESTools.encrypt(requestJson, aes_key);
        System.out.println("加密数据content:");
        System.out.println(content);
        // 加签
        String sign = SHA1WithRSAUtils.sign(content.getBytes("UTF-8"), rsa_privatekey,
                "SHA1WithRSA");
        System.out.println("加签数据sign");
        System.out.println(sign);

        // 平安发送请求给量化派  http://61.50.125.14:9001/app/login (GET/POST)
        // 请求参数中仅包含两个参数：content和sign
        // 量化派接收平安的请求参数
        String requestContent = "dOnmPUThvVzeb019Gvoo5Xlgd5MPhE257DBYFuWM8Dh9IekeYgeMS42CmL6rfpPWMU0DP0OIRJiR4s32mQpyYHa3Dt/sXSxf44IdvwW3uS7l+SEdn4KMB3z95n9hiU4QNyUG5IigcDnUaAQJDF6/b2IehQ2bHmA7P3QjiWipZrdBgqZMA7Wyf5/zhLwNCdyk";
        String requestSign = "H/T4wjK4DBt5lgru2rkWcBb78LK1HCWUjpOtYJnns8n1WUWSM+IVgU3h6dr91VgppOWci6p6ZOvDQhjC4JlHul47Kbt/WMOrVhdixMQ4R3OPjphG7oBbpc7KJ2A+df2EktR5G+7+PB3MVBLKeoEAxgojfDdCOqNYC1XEvAIsrbEN5EjLZJ92FCKbPTLq35SITGLiRR1GFB85kHueL81bXnzHiLfRRIrQjmoOgqR54JrCUN/gqbuI/zOOeIDEoN1PjplWf2AMFmFloZSQNopmgnA1W7dlw1msQciiPwGQ8/WJvexvHHtaESm9uL8YxrWyQxyyh6xx3T6eLhQlRve2JA==";
//		String requestContent=content;
//		String requestSign=sign;

        // 验签 （验签失败作返回异常，不做解密）
        boolean verify = SHA1WithRSAUtils.verify(requestContent.getBytes("UTF-8"),
                ras_publickey, requestSign, "SHA1WithRSA");
        System.out.println("验签结果verify：" + verify);
        if (!verify) {
            throw new Exception("验签失败");
        }

        // 解密
        String requestData = AESTools.decrypt(requestContent, aes_key);
        ;
        System.out.println("解密后数据requestData:");
        System.out.println(requestData);

        // 量化派业务处理，返回数据给平安，需要对返回数据做加密加签,返回参数中仅包含两个参数：content和sign


    }

    private static void genAesKey() throws Exception {
        String aseKey = AESTools.getKeyStr();
        System.out.println("AES密钥：" + aseKey);
    }

    private static void genRsaKeyPair() throws Exception {
        Map<String, Object> keyPairMap = SHA1WithRSAUtils.genKeyPair();
        String privateKey = SHA1WithRSAUtils.getPrivateKey(keyPairMap);
        String publicKey = SHA1WithRSAUtils.getPublicKey(keyPairMap);
        System.out.println("privateKey:");
        System.out.println(privateKey);
        System.out.println("publicKey:");
        System.out.println(publicKey);
    }

    public static void encryptAndDecrypt(String aes_key)
            throws Exception {
        String data = "1234567890";
        // 加密
        String mw = AESTools.encrypt(data, aes_key);
        System.out.println("密文:" + mw);

        // 解密
        String jm = AESTools.decrypt(mw, aes_key);
        System.out.println("明文:" + jm);
    }

    public static void signAndVerify(String publickey, String privatekey)
            throws UnsupportedEncodingException, Exception {

        String key = "1234567890";
        // 私钥加签
        String sign = SHA1WithRSAUtils.sign(key.getBytes("UTF-8"), privatekey,
                "SHA1WithRSA");
        System.out.println(sign);

        String data = key;
        // 公钥验签  true表示验签通过 false表示验签不通过
        System.out.println(SHA1WithRSAUtils.verify(data.getBytes("UTF-8"),
                publickey, sign, "SHA1WithRSA"));

    }

}
 
