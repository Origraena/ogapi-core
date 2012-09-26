package ori.ogapi.report;

import java.io.PrintWriter;

public class Main {
	public static void main(String args[]) {
		Reporter r = new PrintReporter(new PrintWriter(System.out));
		r.newSection("First sec");
		r.report("ceci est un test");
		r.incSection("SECTION");
		r.report("ceci est indenté");
		r.report(" a la suite");
		r.incSection();
		r.report("indent niveau 2");
		r.newSection("section");
		r.decSection();
		r.report("dec");
		r.newSection();
		r.report("new section");
		r.newLine();
		r.report("new line");
		//r.newLine();
		
		r.newReport();
		StdBulleting b = new StdBulleting();
		b.addBullet("* ");
		b.addBullet("# ");
		b.addBullet("> ");
		r.setBulleting(b);
		r.newSection("First sec");
		r.report("ceci est un test");
		r.incSection("SECTION");
		r.report("ceci est indenté");
		r.report(" a la suite");
		r.incSection();
		r.report("indent niveau 2");
		r.newSection("section");
		r.decSection();
		r.report("dec");
		r.newSection();
		r.report("new section");
		r.newLine();
		r.report("new line");
		r.newLine();
		r.incSection();
		r.newSection("eh");
		r.incSection();
		r.newSection("ah");
		r.newSection("ah");
		r.incSection();
		r.newSection("oh");
		r.newSection("oh");
		r.incSection();
		r.newSection("eh");
	

	}
};

