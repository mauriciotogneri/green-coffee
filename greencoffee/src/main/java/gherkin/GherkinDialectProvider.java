package gherkin;

import java.util.List;
import java.util.Map;

import gherkin.ast.Location;
import gherkin.deps.com.google.gson.Gson;

public class GherkinDialectProvider implements IGherkinDialectProvider {
    private static Map<String, Map<String, List<String>>> DIALECTS;
    private final String default_dialect_name;

    static {
        Gson gson = new Gson();
        try {
            //Reader dialects = new InputStreamReader(GherkinDialectProvider.class.getResourceAsStream("/gherkin/gherkin-languages.json"), "UTF-8");
            String dialects = "{\n" +
                    "  \"af\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"En \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Agtergrond\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Maar \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Voorbeelde\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Funksie\",\n" +
                    "      \"Besigheid Behoefte\",\n" +
                    "      \"Vermoë\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Gegewe \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Afrikaans\",\n" +
                    "    \"native\": \"Afrikaans\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Situasie\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Situasie Uiteensetting\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dan \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Wanneer \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"am\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Եվ \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Կոնտեքստ\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Բայց \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Օրինակներ\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Ֆունկցիոնալություն\",\n" +
                    "      \"Հատկություն\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Դիցուք \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Armenian\",\n" +
                    "    \"native\": \"հայերեն\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Սցենար\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Սցենարի կառուցվացքը\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ապա \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Եթե \",\n" +
                    "      \"Երբ \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ar\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"و \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"الخلفية\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"لكن \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"امثلة\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"خاصية\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"بفرض \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Arabic\",\n" +
                    "    \"native\": \"العربية\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"سيناريو\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"سيناريو مخطط\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"اذاً \",\n" +
                    "      \"ثم \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"متى \",\n" +
                    "      \"عندما \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ast\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Y \",\n" +
                    "      \"Ya \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Antecedentes\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Peru \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Exemplos\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Carauterística\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dáu \",\n" +
                    "      \"Dada \",\n" +
                    "      \"Daos \",\n" +
                    "      \"Daes \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Asturian\",\n" +
                    "    \"native\": \"asturianu\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Casu\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Esbozu del casu\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Entós \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Cuando \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"az\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Və \",\n" +
                    "      \"Həm \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Keçmiş\",\n" +
                    "      \"Kontekst\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Amma \",\n" +
                    "      \"Ancaq \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Nümunələr\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Özəllik\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tutaq ki \",\n" +
                    "      \"Verilir \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Azerbaijani\",\n" +
                    "    \"native\": \"Azərbaycanca\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Ssenari\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Ssenarinin strukturu\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"O halda \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Əgər \",\n" +
                    "      \"Nə vaxt ki \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"bg\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"И \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Предистория\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Но \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Примери\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Функционалност\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Дадено \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Bulgarian\",\n" +
                    "    \"native\": \"български\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Сценарий\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Рамка на сценарий\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"То \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Когато \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"bm\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dan \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Latar Belakang\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tetapi \",\n" +
                    "      \"Tapi \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Contoh\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Fungsi\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Diberi \",\n" +
                    "      \"Bagi \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Malay\",\n" +
                    "    \"native\": \"Bahasa Melayu\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Senario\",\n" +
                    "      \"Situasi\",\n" +
                    "      \"Keadaan\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Kerangka Senario\",\n" +
                    "      \"Kerangka Situasi\",\n" +
                    "      \"Kerangka Keadaan\",\n" +
                    "      \"Garis Panduan Senario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Maka \",\n" +
                    "      \"Kemudian \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Apabila \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"bs\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"I \",\n" +
                    "      \"A \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Pozadina\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ali \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Primjeri\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Karakteristika\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dato \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Bosnian\",\n" +
                    "    \"native\": \"Bosanski\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenariju\",\n" +
                    "      \"Scenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Scenariju-obris\",\n" +
                    "      \"Scenario-outline\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Zatim \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kada \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ca\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"I \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Rerefons\",\n" +
                    "      \"Antecedents\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Però \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Exemples\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Característica\",\n" +
                    "      \"Funcionalitat\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Donat \",\n" +
                    "      \"Donada \",\n" +
                    "      \"Atès \",\n" +
                    "      \"Atesa \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Catalan\",\n" +
                    "    \"native\": \"català\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Escenari\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Esquema de l'escenari\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Aleshores \",\n" +
                    "      \"Cal \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Quan \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"cs\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"A také \",\n" +
                    "      \"A \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Pozadí\",\n" +
                    "      \"Kontext\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ale \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Příklady\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Požadavek\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Pokud \",\n" +
                    "      \"Za předpokladu \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Czech\",\n" +
                    "    \"native\": \"Česky\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scénář\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Náčrt Scénáře\",\n" +
                    "      \"Osnova scénáře\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Pak \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Když \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"cy-GB\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"A \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Cefndir\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ond \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Enghreifftiau\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Arwedd\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Anrhegedig a \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Welsh\",\n" +
                    "    \"native\": \"Cymraeg\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Scenario Amlinellol\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Yna \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Pryd \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"da\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Og \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Baggrund\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Men \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Eksempler\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Egenskab\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Givet \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Danish\",\n" +
                    "    \"native\": \"dansk\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenarie\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Abstrakt Scenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Så \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Når \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"de\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Und \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Grundlage\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Aber \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Beispiele\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Funktionalität\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Angenommen \",\n" +
                    "      \"Gegeben sei \",\n" +
                    "      \"Gegeben seien \"\n" +
                    "    ],\n" +
                    "    \"name\": \"German\",\n" +
                    "    \"native\": \"Deutsch\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Szenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Szenariogrundriss\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dann \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Wenn \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"el\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Και \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Υπόβαθρο\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Αλλά \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Παραδείγματα\",\n" +
                    "      \"Σενάρια\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Δυνατότητα\",\n" +
                    "      \"Λειτουργία\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Δεδομένου \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Greek\",\n" +
                    "    \"native\": \"Ελληνικά\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Σενάριο\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Περιγραφή Σεναρίου\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Τότε \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Όταν \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"em\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"\uD83D\uDE02\"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"\uD83D\uDCA4\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"\uD83D\uDE14\"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"\uD83D\uDCD3\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"\uD83D\uDCDA\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"\uD83D\uDE10\"\n" +
                    "    ],\n" +
                    "    \"name\": \"Emoji\",\n" +
                    "    \"native\": \"\uD83D\uDE00\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"\uD83D\uDCD5\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"\uD83D\uDCD6\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"\uD83D\uDE4F\"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"\uD83C\uDFAC\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"en\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"And \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Background\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"But \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Examples\",\n" +
                    "      \"Scenarios\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Feature\",\n" +
                    "      \"Business Need\",\n" +
                    "      \"Ability\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Given \"\n" +
                    "    ],\n" +
                    "    \"name\": \"English\",\n" +
                    "    \"native\": \"English\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Scenario Outline\",\n" +
                    "      \"Scenario Template\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Then \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"When \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"en-Scouse\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"An \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Dis is what went down\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Buh \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Examples\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Feature\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Givun \",\n" +
                    "      \"Youse know when youse got \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Scouse\",\n" +
                    "    \"native\": \"Scouse\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"The thing of it is\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Wharrimean is\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dun \",\n" +
                    "      \"Den youse gotta \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Wun \",\n" +
                    "      \"Youse know like when \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"en-au\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Too right \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"First off\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Yeah nah \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"You'll wanna\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Pretty much\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Y'know \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Australian\",\n" +
                    "    \"native\": \"Australian\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Awww, look mate\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Reckon it's like\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"But at the end of the day I reckon \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"It's just unbelievable \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"en-lol\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"AN \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"B4\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"BUT \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"EXAMPLZ\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"OH HAI\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"I CAN HAZ \"\n" +
                    "    ],\n" +
                    "    \"name\": \"LOLCAT\",\n" +
                    "    \"native\": \"LOLCAT\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"MISHUN\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"MISHUN SRSLY\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"DEN \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"WEN \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"en-old\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ond \",\n" +
                    "      \"7 \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Aer\",\n" +
                    "      \"Ær\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ac \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Se the\",\n" +
                    "      \"Se þe\",\n" +
                    "      \"Se ðe\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Hwaet\",\n" +
                    "      \"Hwæt\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Thurh \",\n" +
                    "      \"Þurh \",\n" +
                    "      \"Ðurh \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Old English\",\n" +
                    "    \"native\": \"Englisc\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Swa\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Swa hwaer swa\",\n" +
                    "      \"Swa hwær swa\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tha \",\n" +
                    "      \"Þa \",\n" +
                    "      \"Ða \",\n" +
                    "      \"Tha the \",\n" +
                    "      \"Þa þe \",\n" +
                    "      \"Ða ðe \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tha \",\n" +
                    "      \"Þa \",\n" +
                    "      \"Ða \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"en-pirate\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Aye \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Yo-ho-ho\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Avast! \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Dead men tell no tales\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Ahoy matey!\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Gangway! \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Pirate\",\n" +
                    "    \"native\": \"Pirate\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Heave to\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Shiver me timbers\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Let go and haul \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Blimey! \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"eo\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kaj \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Fono\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Sed \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Ekzemploj\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Trajto\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Donitaĵo \",\n" +
                    "      \"Komence \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Esperanto\",\n" +
                    "    \"native\": \"Esperanto\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenaro\",\n" +
                    "      \"Kazo\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Konturo de la scenaro\",\n" +
                    "      \"Skizo\",\n" +
                    "      \"Kazo-skizo\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Do \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Se \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"es\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Y \",\n" +
                    "      \"E \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Antecedentes\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Pero \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Ejemplos\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Característica\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dado \",\n" +
                    "      \"Dada \",\n" +
                    "      \"Dados \",\n" +
                    "      \"Dadas \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Spanish\",\n" +
                    "    \"native\": \"español\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Escenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Esquema del escenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Entonces \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Cuando \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"et\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ja \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Taust\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kuid \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Juhtumid\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Omadus\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Eeldades \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Estonian\",\n" +
                    "    \"native\": \"eesti keel\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Stsenaarium\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Raamstsenaarium\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Siis \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kui \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"fa\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"و \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"زمینه\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"اما \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"نمونه ها\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"وِیژگی\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"با فرض \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Persian\",\n" +
                    "    \"native\": \"فارسی\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"سناریو\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"الگوی سناریو\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"آنگاه \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"هنگامی \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"fi\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ja \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Tausta\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Mutta \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Tapaukset\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Ominaisuus\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Oletetaan \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Finnish\",\n" +
                    "    \"native\": \"suomi\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Tapaus\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Tapausaihio\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Niin \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kun \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"fr\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Et que \",\n" +
                    "      \"Et qu'\",\n" +
                    "      \"Et \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Contexte\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Mais que \",\n" +
                    "      \"Mais qu'\",\n" +
                    "      \"Mais \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Exemples\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Fonctionnalité\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Soit \",\n" +
                    "      \"Etant donné que \",\n" +
                    "      \"Etant donné qu'\",\n" +
                    "      \"Etant donné \",\n" +
                    "      \"Etant donnée \",\n" +
                    "      \"Etant donnés \",\n" +
                    "      \"Etant données \",\n" +
                    "      \"Étant donné que \",\n" +
                    "      \"Étant donné qu'\",\n" +
                    "      \"Étant donné \",\n" +
                    "      \"Étant donnée \",\n" +
                    "      \"Étant donnés \",\n" +
                    "      \"Étant données \"\n" +
                    "    ],\n" +
                    "    \"name\": \"French\",\n" +
                    "    \"native\": \"français\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scénario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Plan du scénario\",\n" +
                    "      \"Plan du Scénario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Alors \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Quand \",\n" +
                    "      \"Lorsque \",\n" +
                    "      \"Lorsqu'\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ga\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Agus\"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Cúlra\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ach\"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Samplaí\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Gné\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Cuir i gcás go\",\n" +
                    "      \"Cuir i gcás nach\",\n" +
                    "      \"Cuir i gcás gur\",\n" +
                    "      \"Cuir i gcás nár\"\n" +
                    "    ],\n" +
                    "    \"name\": \"Irish\",\n" +
                    "    \"native\": \"Gaeilge\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Cás\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Cás Achomair\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ansin\"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Nuair a\",\n" +
                    "      \"Nuair nach\",\n" +
                    "      \"Nuair ba\",\n" +
                    "      \"Nuair nár\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"gj\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"અને \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"બેકગ્રાઉન્ડ\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"પણ \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"ઉદાહરણો\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"લક્ષણ\",\n" +
                    "      \"વ્યાપાર જરૂર\",\n" +
                    "      \"ક્ષમતા\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"આપેલ છે \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Gujarati\",\n" +
                    "    \"native\": \"ગુજરાતી\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"સ્થિતિ\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"પરિદ્દશ્ય રૂપરેખા\",\n" +
                    "      \"પરિદ્દશ્ય ઢાંચો\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"પછી \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"ક્યારે \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"gl\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"E \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Contexto\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Mais \",\n" +
                    "      \"Pero \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Exemplos\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Característica\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dado \",\n" +
                    "      \"Dada \",\n" +
                    "      \"Dados \",\n" +
                    "      \"Dadas \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Galician\",\n" +
                    "    \"native\": \"galego\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Escenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Esbozo do escenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Entón \",\n" +
                    "      \"Logo \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Cando \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"he\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"וגם \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"רקע\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"אבל \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"דוגמאות\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"תכונה\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"בהינתן \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Hebrew\",\n" +
                    "    \"native\": \"עברית\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"תרחיש\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"תבנית תרחיש\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"אז \",\n" +
                    "      \"אזי \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"כאשר \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"hi\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"और \",\n" +
                    "      \"तथा \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"पृष्ठभूमि\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"पर \",\n" +
                    "      \"परन्तु \",\n" +
                    "      \"किन्तु \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"उदाहरण\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"रूप लेख\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"अगर \",\n" +
                    "      \"यदि \",\n" +
                    "      \"चूंकि \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Hindi\",\n" +
                    "    \"native\": \"हिंदी\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"परिदृश्य\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"परिदृश्य रूपरेखा\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"तब \",\n" +
                    "      \"तदा \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"जब \",\n" +
                    "      \"कदा \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"hr\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"I \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Pozadina\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ali \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Primjeri\",\n" +
                    "      \"Scenariji\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Osobina\",\n" +
                    "      \"Mogućnost\",\n" +
                    "      \"Mogucnost\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Zadan \",\n" +
                    "      \"Zadani \",\n" +
                    "      \"Zadano \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Croatian\",\n" +
                    "    \"native\": \"hrvatski\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenarij\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Skica\",\n" +
                    "      \"Koncept\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Onda \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kada \",\n" +
                    "      \"Kad \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ht\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ak \",\n" +
                    "      \"Epi \",\n" +
                    "      \"E \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Kontèks\",\n" +
                    "      \"Istorik\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Men \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Egzanp\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Karakteristik\",\n" +
                    "      \"Mak\",\n" +
                    "      \"Fonksyonalite\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Sipoze \",\n" +
                    "      \"Sipoze ke \",\n" +
                    "      \"Sipoze Ke \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Creole\",\n" +
                    "    \"native\": \"kreyòl\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Senaryo\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Plan senaryo\",\n" +
                    "      \"Plan Senaryo\",\n" +
                    "      \"Senaryo deskripsyon\",\n" +
                    "      \"Senaryo Deskripsyon\",\n" +
                    "      \"Dyagram senaryo\",\n" +
                    "      \"Dyagram Senaryo\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Lè sa a \",\n" +
                    "      \"Le sa a \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Lè \",\n" +
                    "      \"Le \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"hu\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"És \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Háttér\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"De \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Példák\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Jellemző\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Amennyiben \",\n" +
                    "      \"Adott \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Hungarian\",\n" +
                    "    \"native\": \"magyar\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Forgatókönyv\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Forgatókönyv vázlat\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Akkor \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Majd \",\n" +
                    "      \"Ha \",\n" +
                    "      \"Amikor \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"id\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dan \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Dasar\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tapi \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Contoh\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Fitur\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dengan \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Indonesian\",\n" +
                    "    \"native\": \"Bahasa Indonesia\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Skenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Skenario konsep\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Maka \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ketika \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"is\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Og \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Bakgrunnur\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"En \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Dæmi\",\n" +
                    "      \"Atburðarásir\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Eiginleiki\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ef \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Icelandic\",\n" +
                    "    \"native\": \"Íslenska\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Atburðarás\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Lýsing Atburðarásar\",\n" +
                    "      \"Lýsing Dæma\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Þá \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Þegar \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"it\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"E \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Contesto\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ma \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Esempi\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Funzionalità\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dato \",\n" +
                    "      \"Data \",\n" +
                    "      \"Dati \",\n" +
                    "      \"Date \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Italian\",\n" +
                    "    \"native\": \"italiano\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Schema dello scenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Allora \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Quando \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ja\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"かつ\"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"背景\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"しかし\",\n" +
                    "      \"但し\",\n" +
                    "      \"ただし\"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"例\",\n" +
                    "      \"サンプル\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"フィーチャ\",\n" +
                    "      \"機能\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"前提\"\n" +
                    "    ],\n" +
                    "    \"name\": \"Japanese\",\n" +
                    "    \"native\": \"日本語\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"シナリオ\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"シナリオアウトライン\",\n" +
                    "      \"シナリオテンプレート\",\n" +
                    "      \"テンプレ\",\n" +
                    "      \"シナリオテンプレ\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"ならば\"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"もし\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"jv\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Lan \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Dasar\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tapi \",\n" +
                    "      \"Nanging \",\n" +
                    "      \"Ananging \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Conto\",\n" +
                    "      \"Contone\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Fitur\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Nalika \",\n" +
                    "      \"Nalikaning \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Javanese\",\n" +
                    "    \"native\": \"Basa Jawa\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Skenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Konsep skenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Njuk \",\n" +
                    "      \"Banjur \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Manawa \",\n" +
                    "      \"Menawa \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"kn\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"ಮತ್ತು \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"ಹಿನ್ನೆಲೆ\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"ಆದರೆ \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"ಉದಾಹರಣೆಗಳು\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"ಹೆಚ್ಚಳ\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"ನೀಡಿದ \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Kannada\",\n" +
                    "    \"native\": \"ಕನ್ನಡ\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"ಕಥಾಸಾರಾಂಶ\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"ವಿವರಣೆ\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"ನಂತರ \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"ಸ್ಥಿತಿಯನ್ನು \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ko\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"그리고\"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"배경\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"하지만\",\n" +
                    "      \"단\"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"예\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"기능\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"조건\",\n" +
                    "      \"먼저\"\n" +
                    "    ],\n" +
                    "    \"name\": \"Korean\",\n" +
                    "    \"native\": \"한국어\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"시나리오\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"시나리오 개요\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"그러면\"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"만일\",\n" +
                    "      \"만약\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"lt\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ir \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Kontekstas\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Bet \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Pavyzdžiai\",\n" +
                    "      \"Scenarijai\",\n" +
                    "      \"Variantai\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Savybė\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Duota \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Lithuanian\",\n" +
                    "    \"native\": \"lietuvių kalba\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenarijus\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Scenarijaus šablonas\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tada \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kai \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"lu\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"an \",\n" +
                    "      \"a \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Hannergrond\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"awer \",\n" +
                    "      \"mä \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Beispiller\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Funktionalitéit\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"ugeholl \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Luxemburgish\",\n" +
                    "    \"native\": \"Lëtzebuergesch\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Szenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Plang vum Szenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"dann \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"wann \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"lv\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Un \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Konteksts\",\n" +
                    "      \"Situācija\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Bet \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Piemēri\",\n" +
                    "      \"Paraugs\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Funkcionalitāte\",\n" +
                    "      \"Fīča\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kad \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Latvian\",\n" +
                    "    \"native\": \"latviešu\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenārijs\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Scenārijs pēc parauga\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tad \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ja \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"mn\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Мөн \",\n" +
                    "      \"Тэгээд \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Агуулга\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Гэхдээ \",\n" +
                    "      \"Харин \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Тухайлбал\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Функц\",\n" +
                    "      \"Функционал\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Өгөгдсөн нь \",\n" +
                    "      \"Анх \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Mongolian\",\n" +
                    "    \"native\": \"монгол\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Сценар\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Сценарын төлөвлөгөө\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Тэгэхэд \",\n" +
                    "      \"Үүний дараа \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Хэрэв \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"nl\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"En \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Achtergrond\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Maar \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Voorbeelden\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Functionaliteit\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Gegeven \",\n" +
                    "      \"Stel \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Dutch\",\n" +
                    "    \"native\": \"Nederlands\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Abstract Scenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dan \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Als \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"no\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Og \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Bakgrunn\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Men \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Eksempler\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Egenskap\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Gitt \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Norwegian\",\n" +
                    "    \"native\": \"norsk\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Scenariomal\",\n" +
                    "      \"Abstrakt Scenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Så \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Når \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"pa\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"ਅਤੇ \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"ਪਿਛੋਕੜ\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"ਪਰ \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"ਉਦਾਹਰਨਾਂ\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"ਖਾਸੀਅਤ\",\n" +
                    "      \"ਮੁਹਾਂਦਰਾ\",\n" +
                    "      \"ਨਕਸ਼ ਨੁਹਾਰ\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"ਜੇਕਰ \",\n" +
                    "      \"ਜਿਵੇਂ ਕਿ \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Panjabi\",\n" +
                    "    \"native\": \"ਪੰਜਾਬੀ\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"ਪਟਕਥਾ\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"ਪਟਕਥਾ ਢਾਂਚਾ\",\n" +
                    "      \"ਪਟਕਥਾ ਰੂਪ ਰੇਖਾ\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"ਤਦ \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"ਜਦੋਂ \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"pl\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Oraz \",\n" +
                    "      \"I \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Założenia\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ale \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Przykłady\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Właściwość\",\n" +
                    "      \"Funkcja\",\n" +
                    "      \"Aspekt\",\n" +
                    "      \"Potrzeba biznesowa\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Zakładając \",\n" +
                    "      \"Mając \",\n" +
                    "      \"Zakładając, że \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Polish\",\n" +
                    "    \"native\": \"polski\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenariusz\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Szablon scenariusza\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Wtedy \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Jeżeli \",\n" +
                    "      \"Jeśli \",\n" +
                    "      \"Gdy \",\n" +
                    "      \"Kiedy \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"pt\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"E \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Contexto\",\n" +
                    "      \"Cenário de Fundo\",\n" +
                    "      \"Cenario de Fundo\",\n" +
                    "      \"Fundo\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Mas \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Exemplos\",\n" +
                    "      \"Cenários\",\n" +
                    "      \"Cenarios\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Funcionalidade\",\n" +
                    "      \"Característica\",\n" +
                    "      \"Caracteristica\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dado \",\n" +
                    "      \"Dada \",\n" +
                    "      \"Dados \",\n" +
                    "      \"Dadas \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Portuguese\",\n" +
                    "    \"native\": \"português\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Cenário\",\n" +
                    "      \"Cenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Esquema do Cenário\",\n" +
                    "      \"Esquema do Cenario\",\n" +
                    "      \"Delineação do Cenário\",\n" +
                    "      \"Delineacao do Cenario\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Então \",\n" +
                    "      \"Entao \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Quando \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ro\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Si \",\n" +
                    "      \"Și \",\n" +
                    "      \"Şi \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Context\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Dar \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Exemple\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Functionalitate\",\n" +
                    "      \"Funcționalitate\",\n" +
                    "      \"Funcţionalitate\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Date fiind \",\n" +
                    "      \"Dat fiind \",\n" +
                    "      \"Dati fiind \",\n" +
                    "      \"Dați fiind \",\n" +
                    "      \"Daţi fiind \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Romanian\",\n" +
                    "    \"native\": \"română\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenariu\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Structura scenariu\",\n" +
                    "      \"Structură scenariu\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Atunci \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Cand \",\n" +
                    "      \"Când \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ru\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"И \",\n" +
                    "      \"К тому же \",\n" +
                    "      \"Также \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Предыстория\",\n" +
                    "      \"Контекст\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Но \",\n" +
                    "      \"А \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Примеры\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Функция\",\n" +
                    "      \"Функциональность\",\n" +
                    "      \"Функционал\",\n" +
                    "      \"Свойство\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Допустим \",\n" +
                    "      \"Дано \",\n" +
                    "      \"Пусть \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Russian\",\n" +
                    "    \"native\": \"русский\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Сценарий\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Структура сценария\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"То \",\n" +
                    "      \"Тогда \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Если \",\n" +
                    "      \"Когда \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"sk\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"A \",\n" +
                    "      \"A tiež \",\n" +
                    "      \"A taktiež \",\n" +
                    "      \"A zároveň \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Pozadie\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ale \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Príklady\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Požiadavka\",\n" +
                    "      \"Funkcia\",\n" +
                    "      \"Vlastnosť\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Pokiaľ \",\n" +
                    "      \"Za predpokladu \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Slovak\",\n" +
                    "    \"native\": \"Slovensky\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenár\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Náčrt Scenáru\",\n" +
                    "      \"Náčrt Scenára\",\n" +
                    "      \"Osnova Scenára\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Tak \",\n" +
                    "      \"Potom \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Keď \",\n" +
                    "      \"Ak \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"sl\": {\n" +
                    "    \"and\": [\n" +
                    "      \"In \",\n" +
                    "      \"Ter \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Kontekst\",\n" +
                    "      \"Osnova\",\n" +
                    "      \"Ozadje\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"Toda \",\n" +
                    "      \"Ampak \",\n" +
                    "      \"Vendar \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Primeri\",\n" +
                    "      \"Scenariji\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Funkcionalnost\",\n" +
                    "      \"Funkcija\",\n" +
                    "      \"Možnosti\",\n" +
                    "      \"Moznosti\",\n" +
                    "      \"Lastnost\",\n" +
                    "      \"Značilnost\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"Dano \",\n" +
                    "      \"Podano \",\n" +
                    "      \"Zaradi \",\n" +
                    "      \"Privzeto \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Slovenian\",\n" +
                    "    \"native\": \"Slovenski\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenarij\",\n" +
                    "      \"Primer\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Struktura scenarija\",\n" +
                    "      \"Skica\",\n" +
                    "      \"Koncept\",\n" +
                    "      \"Oris scenarija\",\n" +
                    "      \"Osnutek\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"Nato \",\n" +
                    "      \"Potem \",\n" +
                    "      \"Takrat \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"Ko \",\n" +
                    "      \"Ce \",\n" +
                    "      \"Če \",\n" +
                    "      \"Kadar \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"sr-Cyrl\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"И \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Контекст\",\n" +
                    "      \"Основа\",\n" +
                    "      \"Позадина\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Али \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Примери\",\n" +
                    "      \"Сценарији\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Функционалност\",\n" +
                    "      \"Могућност\",\n" +
                    "      \"Особина\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"За дато \",\n" +
                    "      \"За дате \",\n" +
                    "      \"За дати \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Serbian\",\n" +
                    "    \"native\": \"Српски\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Сценарио\",\n" +
                    "      \"Пример\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Структура сценарија\",\n" +
                    "      \"Скица\",\n" +
                    "      \"Концепт\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Онда \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Када \",\n" +
                    "      \"Кад \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"sr-Latn\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"I \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Kontekst\",\n" +
                    "      \"Osnova\",\n" +
                    "      \"Pozadina\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ali \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Primeri\",\n" +
                    "      \"Scenariji\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Funkcionalnost\",\n" +
                    "      \"Mogućnost\",\n" +
                    "      \"Mogucnost\",\n" +
                    "      \"Osobina\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Za dato \",\n" +
                    "      \"Za date \",\n" +
                    "      \"Za dati \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Serbian (Latin)\",\n" +
                    "    \"native\": \"Srpski (Latinica)\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenario\",\n" +
                    "      \"Primer\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Struktura scenarija\",\n" +
                    "      \"Skica\",\n" +
                    "      \"Koncept\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Onda \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Kada \",\n" +
                    "      \"Kad \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"sv\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Och \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Bakgrund\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Men \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Exempel\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Egenskap\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Givet \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Swedish\",\n" +
                    "    \"native\": \"Svenska\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Scenario\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Abstrakt Scenario\",\n" +
                    "      \"Scenariomall\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Så \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"När \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ta\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"மேலும்  \",\n" +
                    "      \"மற்றும் \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"பின்னணி\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"ஆனால்  \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"எடுத்துக்காட்டுகள்\",\n" +
                    "      \"காட்சிகள்\",\n" +
                    "      \" நிலைமைகளில்\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"அம்சம்\",\n" +
                    "      \"வணிக தேவை\",\n" +
                    "      \"திறன்\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"கொடுக்கப்பட்ட \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Tamil\",\n" +
                    "    \"native\": \"தமிழ்\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"காட்சி\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"காட்சி சுருக்கம்\",\n" +
                    "      \"காட்சி வார்ப்புரு\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"அப்பொழுது \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"எப்போது \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"th\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"และ \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"แนวคิด\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"แต่ \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"ชุดของตัวอย่าง\",\n" +
                    "      \"ชุดของเหตุการณ์\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"โครงหลัก\",\n" +
                    "      \"ความต้องการทางธุรกิจ\",\n" +
                    "      \"ความสามารถ\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"กำหนดให้ \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Thai\",\n" +
                    "    \"native\": \"ไทย\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"เหตุการณ์\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"สรุปเหตุการณ์\",\n" +
                    "      \"โครงสร้างของเหตุการณ์\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"ดังนั้น \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"เมื่อ \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"tl\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"మరియు \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"నేపథ్యం\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"కాని \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"ఉదాహరణలు\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"గుణము\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"చెప్పబడినది \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Telugu\",\n" +
                    "    \"native\": \"తెలుగు\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"సన్నివేశం\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"కథనం\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"అప్పుడు \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"ఈ పరిస్థితిలో \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"tlh\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"'ej \",\n" +
                    "      \"latlh \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"mo'\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"'ach \",\n" +
                    "      \"'a \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"ghantoH\",\n" +
                    "      \"lutmey\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Qap\",\n" +
                    "      \"Qu'meH 'ut\",\n" +
                    "      \"perbogh\",\n" +
                    "      \"poQbogh malja'\",\n" +
                    "      \"laH\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"ghu' noblu' \",\n" +
                    "      \"DaH ghu' bejlu' \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Klingon\",\n" +
                    "    \"native\": \"tlhIngan\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"lut\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"lut chovnatlh\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"vaj \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"qaSDI' \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"tr\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ve \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Geçmiş\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Fakat \",\n" +
                    "      \"Ama \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Örnekler\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Özellik\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Diyelim ki \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Turkish\",\n" +
                    "    \"native\": \"Türkçe\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Senaryo\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Senaryo taslağı\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"O zaman \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Eğer ki \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"tt\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Һәм \",\n" +
                    "      \"Вә \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Кереш\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ләкин \",\n" +
                    "      \"Әмма \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Үрнәкләр\",\n" +
                    "      \"Мисаллар\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Мөмкинлек\",\n" +
                    "      \"Үзенчәлеклелек\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Әйтик \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Tatar\",\n" +
                    "    \"native\": \"Татарча\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Сценарий\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Сценарийның төзелеше\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Нәтиҗәдә \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Әгәр \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"uk\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"І \",\n" +
                    "      \"А також \",\n" +
                    "      \"Та \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Передумова\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Але \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Приклади\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Функціонал\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Припустимо \",\n" +
                    "      \"Припустимо, що \",\n" +
                    "      \"Нехай \",\n" +
                    "      \"Дано \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Ukrainian\",\n" +
                    "    \"native\": \"Українська\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Сценарій\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Структура сценарію\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"То \",\n" +
                    "      \"Тоді \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Якщо \",\n" +
                    "      \"Коли \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"ur\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"اور \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"پس منظر\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"لیکن \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"مثالیں\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"صلاحیت\",\n" +
                    "      \"کاروبار کی ضرورت\",\n" +
                    "      \"خصوصیت\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"اگر \",\n" +
                    "      \"بالفرض \",\n" +
                    "      \"فرض کیا \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Urdu\",\n" +
                    "    \"native\": \"اردو\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"منظرنامہ\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"منظر نامے کا خاکہ\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"پھر \",\n" +
                    "      \"تب \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"جب \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"uz\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Ва \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Тарих\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Лекин \",\n" +
                    "      \"Бирок \",\n" +
                    "      \"Аммо \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Мисоллар\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Функционал\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Агар \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Uzbek\",\n" +
                    "    \"native\": \"Узбекча\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Сценарий\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Сценарий структураси\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Унда \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Агар \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"vi\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"Và \"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"Bối cảnh\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"Nhưng \"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"Dữ liệu\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"Tính năng\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"Biết \",\n" +
                    "      \"Cho \"\n" +
                    "    ],\n" +
                    "    \"name\": \"Vietnamese\",\n" +
                    "    \"native\": \"Tiếng Việt\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"Tình huống\",\n" +
                    "      \"Kịch bản\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"Khung tình huống\",\n" +
                    "      \"Khung kịch bản\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"Thì \"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"Khi \"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"zh-CN\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"而且\",\n" +
                    "      \"并且\",\n" +
                    "      \"同时\"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"背景\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"但是\"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"例子\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"功能\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"假如\",\n" +
                    "      \"假设\",\n" +
                    "      \"假定\"\n" +
                    "    ],\n" +
                    "    \"name\": \"Chinese simplified\",\n" +
                    "    \"native\": \"简体中文\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"场景\",\n" +
                    "      \"剧本\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"场景大纲\",\n" +
                    "      \"剧本大纲\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"那么\"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"当\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"zh-TW\": {\n" +
                    "    \"and\": [\n" +
                    "      \"* \",\n" +
                    "      \"而且\",\n" +
                    "      \"並且\",\n" +
                    "      \"同時\"\n" +
                    "    ],\n" +
                    "    \"background\": [\n" +
                    "      \"背景\"\n" +
                    "    ],\n" +
                    "    \"but\": [\n" +
                    "      \"* \",\n" +
                    "      \"但是\"\n" +
                    "    ],\n" +
                    "    \"examples\": [\n" +
                    "      \"例子\"\n" +
                    "    ],\n" +
                    "    \"feature\": [\n" +
                    "      \"功能\"\n" +
                    "    ],\n" +
                    "    \"given\": [\n" +
                    "      \"* \",\n" +
                    "      \"假如\",\n" +
                    "      \"假設\",\n" +
                    "      \"假定\"\n" +
                    "    ],\n" +
                    "    \"name\": \"Chinese traditional\",\n" +
                    "    \"native\": \"繁體中文\",\n" +
                    "    \"scenario\": [\n" +
                    "      \"場景\",\n" +
                    "      \"劇本\"\n" +
                    "    ],\n" +
                    "    \"scenarioOutline\": [\n" +
                    "      \"場景大綱\",\n" +
                    "      \"劇本大綱\"\n" +
                    "    ],\n" +
                    "    \"then\": [\n" +
                    "      \"* \",\n" +
                    "      \"那麼\"\n" +
                    "    ],\n" +
                    "    \"when\": [\n" +
                    "      \"* \",\n" +
                    "      \"當\"\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";
            DIALECTS = gson.fromJson(dialects, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GherkinDialectProvider(String default_dialect_name) {
        this.default_dialect_name = default_dialect_name;
    }

    public GherkinDialectProvider() {
        this("en");
    }

    public GherkinDialect getDefaultDialect() {
        return getDialect(default_dialect_name, null);
    }

    @Override
    public GherkinDialect getDialect(String language, Location location) {
        Map<String, List<String>> map = DIALECTS.get(language);
        if (map == null) {
            throw new ParserException.NoSuchLanguageException(language, location);
        }

        return new GherkinDialect(language, map);
    }
}
