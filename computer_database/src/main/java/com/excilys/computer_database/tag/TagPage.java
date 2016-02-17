package com.excilys.computer_database.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * This class is used via taglibs in the JSP in order to display the page links
 * at the bottom of the page. It handles diferent parameters such as the current
 * page, the number of page links to display and the offset (number of items to
 * display)
 * 
 * @author rlarroque
 *
 */
public class TagPage extends SimpleTagSupport {

	private static final int MAX_PAGES_DISPLAYED = 8;

	private String uri;
	private int currentPage;
	private int itemsNumber;
	private int offset;

	private Writer getWriter() {
		JspWriter out = getJspContext().getOut();
		return out;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getItemsNumber() {
		return itemsNumber;
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public void doTag() throws JspException, IOException {
		Writer out = getWriter();

		int totalPages = (itemsNumber / offset);
		totalPages += (itemsNumber % offset);

		boolean isLastPage = (currentPage == totalPages);

		// Define what are the starting and ending pages based on the current
		// page and the number of
		// links displayed.
		int startingPage = Math.max(currentPage - MAX_PAGES_DISPLAYED / 2, 1);
		int endingPage = MAX_PAGES_DISPLAYED + startingPage;

		// If the ending page if beyond the total amount of pages, then shift
		// the pages.
		if (endingPage > totalPages + 1) {

			int diff = endingPage - totalPages;
			startingPage -= diff - 1;

			if (startingPage < 1) {
				startingPage = 1;
			}

			endingPage = totalPages + 1;
		}

		// Write the html output needed to display the page links.
		try {
			out.write("<ul class='pagination'>");

			if (currentPage > 1) {
				out.write(construckLinkPrevious(currentPage - 1));
			}

			for (int i = startingPage; i < endingPage; i++) {
				if (i == currentPage) {
					// Current page is special because it has no links to
					// another page.
					out.write("<li class=\"active\"><a href=\"#\">" + currentPage + "</a></li>");
				} else {
					out.write(constructLink(i));
				}
			}

			if (!isLastPage) {
				out.write(constructLinkNext(currentPage + 1));
			}

			out.write("</ul>");
			
			out.write(constructOffsets(currentPage, offset));

		} catch (java.io.IOException e) {
			throw new JspException("Error in Paginator tag", e);
		}

	}

	/**
	 * Construct a link for the "previous" button
	 * @param page current page
	 * @return html link
	 */
	private String construckLinkPrevious(int page) {
		return constructLink(page, "<span aria-hidden=\"true\">&laquo;</span>", "aria-label=\"Previous\"");
	}

	/**
	 * Construct a link for a page without text or attribute. Text will be the
	 * current page number.
	 * @param page current page
	 * @return html link
	 */
	private String constructLink(int page) {
		return constructLink(page, String.valueOf(page), null);
	}

	/**
	 * Construct a link for a page with text and attribute.
	 * @param page current page
	 * @param text text to display in the link
	 * @param attributes attribute of the link
	 * @return html link
	 */
	private String constructLink(int page, String text, String attributes) {
		StringBuilder link = new StringBuilder("<li");

		link.append(">")
			.append("<a href=\"")
			.append(uri.replace("##1", String.valueOf(page)).replace("##2", String.valueOf(offset)))
			.append("\"");

		if (attributes != null) {
			link.append(" " + attributes);
		}
		
		link.append(">")
			.append(text)
			.append("</a></li>");

		return link.toString();
	}

	/**
	 * Construct a link for the "next" button
	 * @param page current page
	 * @return html link
	 */
	private String constructLinkNext(int page) {
		return constructLink(page, "<span aria-hidden=\"true\">&raquo;</span>", "aria-label=\"Next\"");
	}

	/**
	 * Construct the links to change offsets
	 * @param page current page
	 * @param offset current offset
	 * @return html links for group offset
	 */
	private String constructOffsets(int page, int offset) {
		StringBuilder link = new StringBuilder("<div class=\"btn-group btn-group-sm pull-right\" role=\"group\">");

		link.append("<a href=\"")
				.append(uri.replace("##1", String.valueOf(page)).replace("##2", String.valueOf(10)))
				.append("\" class=\"btn btn-default");

		if (offset == 10) {
			link.append(" active");
		}
		link.append("\">10</a>");

		link.append("<a href=\"")
				.append(uri.replace("##1", String.valueOf(page)).replace("##2", String.valueOf(50)))
				.append("\" class=\"btn btn-default");

		if (offset == 50) {
			link.append(" active");
		}
		link.append("\">50</a>");

		link.append("<a href=\"")
				.append(uri.replace("##1", String.valueOf(page)).replace("##2", String.valueOf(100)))
				.append("\" class=\"btn btn-default");

		if (offset == 100) {
			link.append(" active");
		}
		link.append("\">100</a>");

		link.append("</div>");

		return link.toString();
	}
}
