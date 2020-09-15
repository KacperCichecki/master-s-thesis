package cichecki.kacper.jsonflattener.mvc;

import cichecki.kacper.jsonflattener.persistence.listeners.MyModifiedObjectTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MyHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    MyModifiedObjectTracker myModifiedObjectTracker;

    public MyHandlerInterceptorAdapter(MyModifiedObjectTracker myModifiedObjectTracker) {
        this.myModifiedObjectTracker = myModifiedObjectTracker;
    }

    @Override
    public final boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            ServletException, IOException {

        String method = request.getMethod();

        if (!"GET".equals(method))
            return true;

        String ifNoneMatchHeader = request.getHeader("If-None-Match");
        int currentCounter = myModifiedObjectTracker.getCounter();

        if (StringUtils.isEmpty(ifNoneMatchHeader)) {
            return setUpEtag(currentCounter, response);
        }

        int previousCounter;
        try {
            previousCounter = Integer.parseInt(ifNoneMatchHeader);
        } catch (Exception e) {
            return true;
        }


        // compare previous token with current one
        if (previousCounter == currentCounter) {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            // re-use original last modified timestamp
            response.setHeader("Last-Modified", request.getHeader("If-Modified-Since"));
            return false; // no further processing required
        } else {
            return setUpEtag(currentCounter, response);
        }

    }

    private boolean setUpEtag(int currentCounter, HttpServletResponse response) {
        response.setHeader("ETag", "" + currentCounter);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        Date lastModified = cal.getTime();
        response.setDateHeader("Last-Modified", lastModified.getTime());

        return true;
    }
}
