package ru.gb.april.market.april_market.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Deprecated
public class AppTest {
    private long userServiceTotalDuration;
    private long orderServiceTotalDuration;
    private long productServiceTotalDuration;

    public long getUserServiceTotalDuration() {
        return userServiceTotalDuration;
    }

    public void setUserServiceTotalDuration(long userServiceTotalDuration) {
        this.userServiceTotalDuration = userServiceTotalDuration;
    }

    public long getOrderServiceTotalDuration() {
        return orderServiceTotalDuration;
    }

    public void setOrderServiceTotalDuration(long orderServiceTotalDuration) {
        this.orderServiceTotalDuration = orderServiceTotalDuration;
    }

    public long getProductServiceTotalDuration() {
        return productServiceTotalDuration;
    }

    public void setProductServiceTotalDuration(long productServiceTotalDuration) {
        this.productServiceTotalDuration = productServiceTotalDuration;
    }

    @Around("execution(public * ru.gb.april.market.april_market.services.UserService.*(..))")
    public void timeUserService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("op start for user");
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        userServiceTotalDuration += duration;
        System.out.println("end "+ userServiceTotalDuration);
    }

    @Around("execution(public * ru.gb.april.market.april_market.services.OrderService.*(..))")
    public void timeOrderService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("op start for order");
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        orderServiceTotalDuration += duration;
        System.out.println("end "+ orderServiceTotalDuration);
    }


    @Around("execution(public * ru.gb.april.market.april_market.services.ProductService.*(..))")
    public void timeProductService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("op start for product");
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        productServiceTotalDuration += duration;
        System.out.println("end "+ productServiceTotalDuration);
    }

}