package com.excilys.cdb.taglibs;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport {
    private String uri;
	private int currPage;
	private int totalPages;
	
    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }
    
    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();
 
        try {
            out.write("<ul class=\"pagination\">");
 
            if (currPage > 1)
                out.write(getPreviousLink(currPage - 1));
 
            for (int i = 1 ; i <= totalPages; i++) {
                if (i == currPage)
                    out.write(getLinkTag(currPage, String.valueOf(currPage), "active"));
                else
                    out.write(getLinkTag(i));
            }
 
            if (currPage < totalPages)
                out.write(getNextLink(currPage + 1));
 
            out.write("</ul>");
 
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }
    
    private String getLinkTag(int pageNumber) {
        return getLinkTag(pageNumber, String.valueOf(pageNumber),null);
    }
    
    private String getLinkTag(int pageNumber, String text, String className) {
        StringBuilder link = new StringBuilder("<li");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        link.append("><a href=\"").append(uri.replace("##", String.valueOf(pageNumber))).append("\">")
            .append(text)
            .append("</a></li>");
        return link.toString();
    }

    private String getPreviousLink(int pageNumber){
        StringBuilder link = new StringBuilder("<li");
        link.append(">")
            .append("<a href=\"").append(uri.replace("##", String.valueOf(pageNumber))).append("\" aria-label=\"Previous\">")
            .append("<span aria-hidden=\"true\">&larr; Previous</span>")
            .append("</a></li>");
        return link.toString();
    }
    
    private String getNextLink(int pageNumber){
        StringBuilder link = new StringBuilder("<li");
        link.append(">")
            .append("<a href=\"").append(uri.replace("##", String.valueOf(pageNumber))).append("\" aria-label=\"Next\">")
            .append("<span aria-hidden=\"true\">Next &rarr;</span>")
            .append("</a></li>");
        return link.toString();
    }
    
	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the currPage
	 */
	public int getCurrPage() {
		return currPage;
	}

	/**
	 * @param currPage the currPage to set
	 */
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}    
	
}
