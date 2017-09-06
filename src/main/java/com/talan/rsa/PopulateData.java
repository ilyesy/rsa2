package com.talan.rsa;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.talan.rsa.entity.Chapter;
import com.talan.rsa.entity.Implementation;
import com.talan.rsa.entity.Rule;
import com.talan.rsa.entity.Theme;
import com.talan.rsa.entity.other.ImplementationEnum;
import com.talan.rsa.repository.ChapterRepository;
import com.talan.rsa.repository.ImplementationRepository;
import com.talan.rsa.repository.RuleRepository;
import com.talan.rsa.repository.ThemeRepository;

@Component
public class PopulateData implements ApplicationRunner {
	
	@Autowired
	private ImplementationRepository implementationRepo;
	@Autowired
	private RuleRepository ruleRepo;
	@Autowired
	private ThemeRepository themeRepo;
	@Autowired
	private ChapterRepository chapterRepo;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {

		
		Theme th1 = new Theme("java", "typed lang / oop", null);
		Theme th2 = new Theme("js", "interprated", null);
		Theme th3 = new Theme("C#", "oop", null);
		Theme th4 = new Theme("C", "procedural", null);
		Theme th5 = new Theme("Prolog", "functionnal", null);
		Theme th6 = new Theme("html", "HyperText Markup Language", null);
		Theme th7 = new Theme("C++", "oop", null);
		Theme th8 = new Theme("ruby", "...", null);
		Theme th9 = new Theme("python", "...", null);
		Theme th10 = new Theme("vb", "...", null);
		Theme th11 = new Theme("sql", "...", null);
		themeRepo.save(th1);
		themeRepo.save(th2);
		themeRepo.save(th3);
		themeRepo.save(th4);
		themeRepo.save(th5);
		themeRepo.save(th6);
		themeRepo.save(th7);
		themeRepo.save(th8);
		themeRepo.save(th9);
		themeRepo.save(th10);
		themeRepo.save(th11);
		
		Chapter chap1 = new Chapter("java 7", "java version 7", th1, null);
		Chapter chap2 = new Chapter("java 8", "java version 8", th1, null);
		Chapter chap3 = new Chapter("ECMA 15", "ecmascript 2015", th2, null);
		Chapter chap4 = new Chapter("ECMA 16", "ecmascript 2016", th2, null);
		chapterRepo.save(chap1);
		chapterRepo.save(chap2);
		chapterRepo.save(chap3);
		chapterRepo.save(chap4);
		
		Rule r1 = new Rule("rule1", "in this rule ...", chap1, null);
		Rule r2 = new Rule("rule2", "in this rule ...", chap2, null);
		Rule r3 = new Rule("rule3", "in this rule ...", chap3, null);
		Rule r4 = new Rule("rule4", "in this rule ...", chap4, null);
		ruleRepo.save(r1);
		ruleRepo.save(r2);
		ruleRepo.save(r3);
		ruleRepo.save(r4);
		
		Implementation imp1 = new Implementation("Implementation1", "comm1", ImplementationEnum.NotRecommendedImplementation, r1);
		Implementation imp2 = new Implementation("Implementation2", "comm2", ImplementationEnum.NotRecommendedImplementation, r1);
		Implementation imp3 = new Implementation("Implementation3", "comm3", ImplementationEnum.RecommendedImplementation, r1);
		Implementation imp4 = new Implementation("Implementation4", "comm4", ImplementationEnum.RecommendedImplementation, r1);
		Implementation imp5 = new Implementation("Implementation5", "comm5", ImplementationEnum.RecommendedImplementation, r2);
		Implementation imp6 = new Implementation("Implementation6", "comm6", ImplementationEnum.RecommendedImplementation, r3);
		implementationRepo.save(imp1);
		implementationRepo.save(imp2);
		implementationRepo.save(imp3);
		implementationRepo.save(imp4);
		implementationRepo.save(imp5);
		implementationRepo.save(imp6);
		
		
	}
	
	

}
