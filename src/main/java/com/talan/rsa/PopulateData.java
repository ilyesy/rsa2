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
		themeRepo.save(th1);
		themeRepo.save(th2);
		
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
		implementationRepo.save(imp1);
		implementationRepo.save(imp2);
		implementationRepo.save(imp3);
		implementationRepo.save(imp4);
		
		
	}
	
	

}
