package lab.servlets;

/** Petites fonctions utilitaires pour generer le HTML des servlets. */
public final class Html {

    private Html() {
    }

    public static String header(String title) {
        return "<!DOCTYPE html><html lang=\"fr\"><head><meta charset=\"UTF-8\"><title>" + esc(title)
                + "</title><style>" + CSS + "</style></head><body><h1>" + esc(title) + "</h1>";
    }

    /**
     * CSS en ligne (et non un fichier style.css) : sinon chaque page declencherait une 2e requete
     * HTTP, qui apparaitrait aussi dans les logs de LoggingFilter et RequestListener.
     */
    public static final String CSS = "body{font-family:Segoe UI,Arial,sans-serif;max-width:820px;margin:24px auto;"
            + "padding:0 16px;color:#1f2933}h1{color:#1d4ed8;border-bottom:2px solid #dbe4ff;padding-bottom:6px}"
            + "table{border-collapse:collapse;margin:12px 0}th,td{border:1px solid #cbd5e1;padding:6px 12px;"
            + "text-align:left}th{background:#eef2ff}label{display:block;margin:8px 0}input[type=text]{padding:4px}"
            + "button,input[type=submit]{margin-top:8px;padding:6px 14px}.big{font-size:1.2em}"
            + "code{background:#f1f5f9;padding:1px 4px}.nav{margin-top:24px}";

    public static String footer() {
        return "<p class=\"nav\"><a href=\"index.html\">&larr; Accueil</a></p></body></html>";
    }

    /** Echappe les caracteres speciaux HTML (evite l'injection de HTML/JS). */
    public static String esc(Object o) {
        if (o == null) {
            return "<em>(vide)</em>";
        }
        return o.toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}
