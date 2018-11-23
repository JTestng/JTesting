package com.arjvik.jtesting.web;

import java.util.HashMap;
import java.util.Map;

public class WebpageEntity extends HashMap<String, Object> implements Map<String, Object>{
	
	private static final long serialVersionUID = 1L;
	
	private Session session;
	
	public WebpageEntity(Session session) {
		this.session = session;
	}
	
	public WebpageEntity(Session session, Object... args){
		this(session, null, args);
	}
	
	public WebpageEntity(Session session, Object[] urlParams, Object... args){
		this.session = session;
		for(int i = 0; i < args.length; i += 2)
			put((String) args[i], args[i+1]);
		if(urlParams!=null) {
			Map<String, Object> urls = new HashMap<>();
			put("urls", urls);
			for(int i = 0; i < urlParams.length; i += 2)
				urls.put((String) urlParams[i], urlParams[i+1]);
		}
	}
	
	@Override
	public Object get(Object key) {
		if(key.equals("session"))
			return session;
		else
			return super.get(key);
	}
	
	@Override
	public Object put(String key, Object value) {
		if(key.equals("session")){
			Session oldSession = session;
			session = (Session) value;
			return oldSession;
		} else {
			return super.put(key, value);
		}
	}
}
