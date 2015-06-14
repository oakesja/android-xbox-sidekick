package com.example.joakes.xbox_sidekick.helpers;

/**
 * Created by joakes on 6/14/15.
 */
public class JsonSetup {
    public static String profileJson() {
        return "{\"id\":2533274912330216," +
                "\"hostId\":null," +
                "\"Gamertag\":\"PoizonOakes92\"," +
                "\"GameDisplayName\":\"PoizonOakes92\"," +
                "\"AppDisplayName\":\"PoizonOakes92\"," +
                "\"Gamerscore\":37963," +
                "\"GameDisplayPicRaw\":\"http:\\/\\/images-eds.xboxlive.com\\/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png\"," +
                "\"AppDisplayPicRaw\":\"http:\\/\\/images-eds.xboxlive.com\\/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png\"," +
                "\"AccountTier\":\"Gold\"," +
                "\"XboxOneRep\":\"GoodPlayer\"," +
                "\"PreferredColor\":\"http:\\/\\/dlassets.xboxlive.com\\/public\\/content\\/ppl\\/colors\\/00009.json\"," +
                "\"TenureLevel\":3,\"" +
                "isSponsoredUser\":false" +
                "}";
    }

    public static String xboxOneGameJson() {
        return " {  \n" +
                "         \"lastUnlock\":\"2015-01-13T23:18:03.2588097Z\",\n" +
                "         \"titleId\":1144039928,\n" +
                "         \"serviceConfigId\":\"77290100-225e-4768-9373-98164430a9f8\",\n" +
                "         \"titleType\":\"DGame\",\n" +
                "         \"platform\":\"Durango\",\n" +
                "         \"name\":\"Halo: The Master Chief Collection\",\n" +
                "         \"earnedAchievements\":114,\n" +
                "         \"currentGamerscore\":1025,\n" +
                "         \"maxGamerscore\":6000\n" +
                "      }";
    }

    public static String xbox360GameJson() {
        return "{  \n" +
                "         \"lastPlayed\":\"2014-12-07T23:26:12.9630000Z\",\n" +
                "         \"currentAchievements\":67,\n" +
                "         \"currentGamerscore\":1440,\n" +
                "         \"sequence\":2,\n" +
                "         \"titleId\":1161889984,\n" +
                "         \"titleType\":1,\n" +
                "         \"platforms\":[  \n" +
                "            1\n" +
                "         ],\n" +
                "         \"name\":\"Dragon Age: Origins\",\n" +
                "         \"totalAchievements\":76,\n" +
                "         \"totalGamerscore\":1750\n" +
                "      }";
    }

    public static String xboxOneAchievementJson() {
        return "{  \n" +
                "      \"id\":6,\n" +
                "      \"serviceConfigId\":\"de360100-3ae5-4fd5-a7da-9d3d6b481c3d\",\n" +
                "      \"name\":\"Lilac and Gooseberries\",\n" +
                "      \"titleAssociations\":[  \n" +
                "         {  \n" +
                "            \"name\":\"The Witcher 3: Wild Hunt\",\n" +
                "            \"id\":1799887933\n" +
                "         }\n" +
                "      ],\n" +
                "      \"progressState\":\"Achieved\",\n" +
                "      \"progression\":{  \n" +
                "         \"requirements\":[  \n" +
                "            {  \n" +
                "               \"id\":\"1befad18-a712-4889-8dab-685088a8278f\",\n" +
                "               \"current\":null,\n" +
                "               \"target\":1,\n" +
                "               \"operationType\":\"Sum\",\n" +
                "               \"valueType\":\"Integer\",\n" +
                "               \"ruleParticipationType\":\"Individual\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"timeUnlocked\":\"2015-05-19T03:54:42.5209590Z\"\n" +
                "      },\n" +
                "      \"mediaAssets\":[  \n" +
                "         {  \n" +
                "            \"name\":\"a43be2d3-7d6c-440c-bc75-31fce8457b82.png\",\n" +
                "            \"type\":\"Icon\",\n" +
                "            \"url\":\"http://images-eds.xboxlive.com/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png\"\n" +
                "         }\n" +
                "      ],\n" +
                "      \"platforms\":[  \n" +
                "         \"Durango\"\n" +
                "      ],\n" +
                "      \"isSecret\":true,\n" +
                "      \"description\":\"Find Yennefer of Vengerberg.\",\n" +
                "      \"lockedDescription\":\"Find Yennefer of Vengerberg.\",\n" +
                "      \"productId\":\"810967a8-b286-4eef-b02a-d16bbe55f85f\",\n" +
                "      \"achievementType\":\"Persistent\",\n" +
                "      \"participationType\":\"Individual\",\n" +
                "      \"timeWindow\":null,\n" +
                "      \"rewards\":[  \n" +
                "         {  \n" +
                "            \"name\":null,\n" +
                "            \"description\":null,\n" +
                "            \"value\":15,\n" +
                "            \"type\":\"Gamerscore\",\n" +
                "            \"mediaAsset\":null,\n" +
                "            \"valueType\":\"Int\"\n" +
                "         }\n" +
                "      ],\n" +
                "      \"estimatedTime\":\"00:00:00\",\n" +
                "      \"deeplink\":null,\n" +
                "      \"isRevoked\":false\n" +
                "   }";
    }

    public static String xbox360AchievementJson() {
        return "{  \n" +
                "      \"id\":5,\n" +
                "      \"titleId\":1414793189,\n" +
                "      \"name\":\"Napalm in the Morning\",\n" +
                "      \"sequence\":53,\n" +
                "      \"flags\":1245198,\n" +
                "      \"unlockedOnline\":true,\n" +
                "      \"unlocked\":true,\n" +
                "      \"isSecret\":false,\n" +
                "      \"platform\":1,\n" +
                "      \"gamerscore\":3,\n" +
                "      \"imageId\":21,\n" +
                "      \"description\":\"You have won a battle!\",\n" +
                "      \"lockedDescription\":\"Defeat an enemy unit\",\n" +
                "      \"type\":6,\n" +
                "      \"isRevoked\":false,\n" +
                "      \"timeUnlocked\":\"2014-03-02 00:33:30\",\n" +
                "      \"imageUnlocked\":\"http:\\/\\/image.xboxlive.com\\/global\\/t.545407e5\\/ach\\/0\\/15\"\n" +
                "   }";
    }
}
