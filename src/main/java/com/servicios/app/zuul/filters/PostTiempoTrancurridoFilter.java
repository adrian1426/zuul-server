package com.servicios.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTrancurridoFilter extends ZuulFilter{
	
	private static Logger log=LoggerFactory.getLogger(PostTiempoTrancurridoFilter.class);

	@Override
	public boolean shouldFilter() {
		//evaluación para decidir si ejecutar el prefilter y ejecutar la función run
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx=RequestContext.getCurrentContext();
		HttpServletRequest request= ctx.getRequest();
		
		log.info("Entrando a post filter");
		
		Long tiempoInicio=(Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal=System.currentTimeMillis();
		Long tiempoTranscurrido=tiempoFinal-tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos %s seg.", tiempoTranscurrido.doubleValue()/1000.00));
		return null;
	}

	@Override
	public String filterType() {
		//representa el tipo, son 4 pre,post,route,error
		return "pre";
	}

	@Override
	public int filterOrder() {
		//orden de la ejecución
		return 1;
	}

}
