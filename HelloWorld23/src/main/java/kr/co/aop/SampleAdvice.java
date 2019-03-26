package kr.co.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	@Before("execution(* kr.co.service.MessageService*.*(..))")//join 포인트 설정
	public void StartLog(JoinPoint jp) {
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
		System.out.println(Arrays.toString(jp.getArgs()));
		System.out.println(jp.getKind());
		System.out.println(jp.getTarget());
		System.out.println("시작합니다.");
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
	}
	
	
	//어라운드 어드바이스 사용할 때 주의할 것
	//1. 반환형은 반드시 Object, 2. 반드시 예외처리 해야한다. 3. 파라미터 반드시 ProceedingJoinPoint로 처리
	@Around("execution(* kr.co.service.MessageService*.*(..))") 
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable{
		//예외처리 Throwable.class는 그동안 줄곧 써왔던 Exceptional.class의 부모클래스
			
		long start=System.currentTimeMillis();
			
			Object result = pjp.proceed(); // 이 코드가 제일 중요. 이 코드를 기준으로 위에 있는 코드는 Before, 아래 있는 코드는 After
			
		long end=System.currentTimeMillis();
		System.out.println("========================");
		System.out.println(end-start);
		System.out.println("========================");
		return result;
	}
	
	@After("execution(* kr.co.service.MessageService*.*(..))")//join 포인트 설정
	public void EndLog() {
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
		System.out.println("끝났습니다.");
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
	}
	
	
	
	
}
