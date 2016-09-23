package Components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {

	private int ID = 0;
	private String title;
	private String HTML;
	private String css;

	public Blog(String title, String HTML) {
		ID = (int) System.currentTimeMillis();
		this.title = title;
		this.HTML = HTML;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		StringBuilder htmlTitle = new StringBuilder();
		htmlTitle.append("<title>");
		htmlTitle.append(title);
		htmlTitle.append("</title>");
		return htmlTitle.toString();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHTML() {
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MMM-dd-HH-mm-ss");
		String date = format.format(new Date());
		StringBuilder div = new StringBuilder();
		div.append("<html>");
		div.append("<head>");
		div.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">");
		div.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap-theme.min.css\">");
		div.append("<link rel=\"stylesheet\" type=\"text/css\" href=" + date + ".css" + ">");
		div.append("<script src=\"js/jquery-3.1.0.min.js\"></script>");
		div.append("<script src=\"js/bootstrap.min.js\"></script>");
		div.append("<script src=\"js/npm.js\"></script>");
		div.append(getTitle());
		div.append("</head>");
		div.append("<body>");
		div.append(HTML);
		div.append("</body>");
		div.append("</html>");
		return div.toString();
	}

	public void setHTML(String hTML) {
		HTML = hTML;
	}

	public void addToBlog(String blogFileName, ArrayList<String> styles, ArrayList<String> blogStyles) throws IOException {
		File cssFile = new File("index.css");
		BufferedWriter csswriter = new BufferedWriter(new FileWriter(cssFile));
		StringBuilder css = new StringBuilder();
		css.append("*{margin:0;\n padding:0;}");
		css.append("\n");
		css.append("body {");
		css.append("\n");
		css.append("text-align:center;");
		for (int i = 0; i < styles.size(); i++) {
			css.append(styles.get(i));
			css.append("\n");

		}
		css.append("}");
		css.append("\n");
		css.append("#blogs{" );
		css.append("\n");
		css.append("width:960px;");
		css.append("\n");
		css.append(" margin:0 auto;");
		css.append("\n");
		css.append("text-align:left;");
		css.append("\n");
		for (int i = 0; i < styles.size(); i++) {
			css.append( blogStyles.get(i));
			css.append("\n");

		}
		css.append("}");

		csswriter.write(css.toString());
		csswriter.flush();
		csswriter.close();
		StringBuilder iframe = new StringBuilder();
		File index = new File("index.html");
		Path file = Paths.get(index.toURI());
		if (!index.exists()) {
			index.createNewFile();
			iframe.append("<html>");
			iframe.append("<head>");
			iframe.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">");
			iframe.append("</head>");
			iframe.append("\n");
			iframe.append("<div class=\"background\" ></div>");
			iframe.append("\n");
			iframe.append("<img src=\"\"> </img>");
			iframe.append("\n");
			iframe.append("<div id=\"blogs\"class=\"container\">");
			iframe.append("\n");
			iframe.append("<iframe frameBorder=\"0\" scrolling=\"no\" ");
			iframe.append("src=\"" + blogFileName + "\"" + " width=\"636\" height=\"500\"  align=\"middle\" style=\" margin-left:20%;\"> </iframe>");
			iframe.append("\n");
			iframe.append("</div>");
			iframe.append("\n");
			iframe.append("</html>");
			ArrayList<String> lines = new ArrayList<String>();
			lines.add(iframe.toString());
			Files.write(file, lines, StandardCharsets.UTF_8);
			return;

		} else {
			
			
			iframe.append("<iframe frameBorder=\"0\" scrolling=\"no\" ");
			iframe.append("src=\"" + blogFileName + "\"" + " width=\"636\" height=\"500\"   align=\"middle\" style=\" margin-left:20%;\"> </iframe>");
			iframe.append("\n");

			List<String> lines = Files.readAllLines(file);
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).contains("div id=\"blogs\"class=\"container\"")) {
					lines.add(i += 1, iframe.toString());
				}

			}
			Files.write(file, lines, StandardCharsets.UTF_8);

		}

	}

	public String publish(ArrayList<String> styles, ArrayList<String> blogStyles) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MMM-dd-HH-mm-ss");
			String date = format.format(new Date());

			File htmlFile = new File(date + ".html");
			BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
			writer.write(getHTML());
			writer.flush();
			writer.close();
			File cssFile = new File(date + ".css");
			BufferedWriter csswriter = new BufferedWriter(new FileWriter(cssFile));
			csswriter.write(getCss());
			csswriter.flush();
			csswriter.close();
			addToBlog(htmlFile.getName(), styles, blogStyles);
			return "created files : " + htmlFile.getAbsolutePath() + "   " + cssFile.getAbsolutePath() + ""
					+ htmlFile.getName() + " has been published open index.html";
		} catch (IOException e) {

			return "Failed to published " + e.getMessage();
		}

	}

	public String getCss() {
		return css;
	}

	public void addCSSProperties(ArrayList<String> styles) {
		StringBuilder style = new StringBuilder();
		style.append("#section {");
		for (int i = 0; i < styles.size(); i++) {
			style.append(styles.get(i));
			style.append("\n");

		}
		style.append("}");
		this.css = style.toString();
	}

}
