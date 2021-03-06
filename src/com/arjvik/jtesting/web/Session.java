package com.arjvik.jtesting.web;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session implements Map<String, Object> {

	private HttpSession session;
	
	public Session(HttpSession session) {
		this.session = session;
	}
	
	public Session(HttpServletRequest request) {
		this(request.getSession());
	}
	
	@Override
	public int size() {
		int i = 0;
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements()) {
			e.nextElement();
			i++;
		}
		return i;
	}

	@Override
	public boolean isEmpty() {
		return !session.getAttributeNames().hasMoreElements();
	}

	@Override
	public boolean containsKey(Object key) {
		return session.getAttribute((String) key)!=null;
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object get(Object key) {
		return session.getAttribute((String) key);
	}

	@Override
	public Object put(String key, Object value) {
		if(value==null)
			throw new NullPointerException();
		session.setAttribute(key, value);
		return null;
	}

	@Override
	public Object remove(Object key) {
		session.removeAttribute((String) key);
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		for(Entry<? extends String, ? extends Object> e : m.entrySet())
			put(e.getKey(), e.getValue());
	}

	@Override
	public void clear() {
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements())
			remove(e.nextElement());
	}

	@Override
	public Set<String> keySet() {
		Set<String> s = new HashSet<>();
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements())
			s.add(e.nextElement());
		return Collections.unmodifiableSet(s);
	}

	@Override
	public Collection<Object> values() {
		Set<Object> s = new HashSet<>();
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements())
			s.add(session.getAttribute(e.nextElement()));
		return Collections.unmodifiableSet(s);
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		Set<Entry<String,Object>> s = new HashSet<>();
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			s.add(new AbstractMap.SimpleImmutableEntry<String,Object>(key, session.getAttribute(key)));
		}
		return Collections.unmodifiableSet(s);
	}
	
	

}
