package edu.uwec.cs.morriscm;
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class FirstTag implements Tag, Serializable {
	private static final long serialVersionUID = 1L;
	private PageContext pc = null;
	private Tag parent = null;
	private String name = null;
	private String address = null;

	public void setPageContext(PageContext p) {
		pc = p;
	}
	public void setParent(Tag t) {
		parent = t;
	}
	public Tag getParent() {
		return parent;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getName() {
		return this.name;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	public String getAddress() {
		return address;
	}	
	public int doStartTag() throws JspException {
		try {
			if(name != null && address != null) {
				pc.getOut().write(name + "\n" + address);
			} else {
				pc.getOut().write("You didn't enter your name and address");
				pc.getOut().write(", what are you afraid of ?");
			}
		} catch(IOException e) {
			throw new JspTagException("An IOException occurred.");
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void release() {
		pc = null;
		parent = null;
		name = null;
	}
}
/*
Explanation of FirstTag Class Code
First comes our FirstTag class declaration. Notice that we implement two interfaces; Serializable and Tag. 
We implement method-less interface Serializable so that our FirstTag class can be saved to the disk or 
serialized over the network. Serialization is not a requirement for building JSP tag classes. The next 
interface ( the important one ) which FirstTag class implements is the Tag interface.

Like I said on the first page, our JSP tag class will have to implement one of the two interfaces, the two 
interfaces that I talked about are :

Tag 
BodyTag 
These interfaces can be found in javax.servlet.jsp.tagext package. Tag is a simpler interface with about 
six methods to implement while BodyTag extends Tag interface to add three more methods and lot more features. 
Since we are building a simple JSP tag we will be implementing Tag interface.

There are two classes provided in the javax.servlet.jsp.tagext package which provide default implementation 
for the above two interfaces :

TagSupport 
BodyTagSupport 
Had we wanted we could have extended one of these classes and overridden the methods we needed. But I thought 
we should implement the Tag interface directly in this tutorial because it will aid in your learning about 
how it's different methods are used.

public class FirstTag implements Tag, Serializable {
The six methods which Tag interface provides and which we have to implement are :

setPageContext(PageContext pc) 
setParent(Tag parent) 
getParent() 
doStartTag() 
doEndTag() 
release() 
Above six methods are a must. Now if we have attributes in our tag, like we have in this case i.e. name, then we 
have to add getter and setter methods for that attribute as well like is the case with JavaBean properties :

setName(String s) 
getName() 
In our FirstTag class, we will be using three private variables. Two for saving reference to PageContext and Tag 
objects which are provided to us by setPageContext() and setParent() methods. And one for "name" attribute which 
is of type String.

	private PageContext pc = null;
	private Tag parent = null;
	private String name = null;

In the setPageContext() method we save the reference to PageContext object in the private variable.

	public void setPageContext(PageContext p) {
		pc = p;
	}
In setParent() method we save the reference to parent Tag object in the private variable.

	public void setParent(Tag t) {
		parent = t;
	}
Likewise we code a getParent() method.

	public Tag getParent() {
		return parent;
	}
Getter and setter methods for "name" attribute.

	public void setName(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}
doStartMethod() will be called with the start of the tag. In this method we use PageContext object to write value of 
"name" attribute back to the user. If the user has not entered his/her name in the "name" attribute of the tag then 
display a different message.

We return SKIP_BODY from doStartTag() method as there is no body content in this tag. SKIP_BODY along with three other 
static variables is provided by the Tag interface.

	public int doStartTag() throws JspException {
		try {


		if(name != null) {
			pc.getOut().write("Hello " + name + "!");
		} else {
			pc.getOut().write("You didn't enter your name");
			pc.getOut().write(", what are you afraid of ?");
		}

		} catch(IOException e) {
			throw new JspTagException("An IOException occurred.");
		}
		return SKIP_BODY;
	}
doEndTag() is called when end of the tag is reached. It returns EVAL_PAGE so that rest of the page is read by the server.

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
Lastly, we code the release() method. This method is called by the JSP page when all the methods of the tag class 
have been called and it is time to free resources. In this method you should free the resources you may have accumulated 
during the execution of other methods of the class.

	public void release() {
		pc = null;
		parent = null;
		name = null;
	}
}
Tag Library Descriptor
A Tag Library Descriptor ( or simply TLD ) file is a simple XML file which provides details about your JSP tag/s. 
Create a new DemoTags.tld file in the /WEB-INF/tlds folder. Copy and paste the following text in it :

<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE taglib PUBLIC "-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.1//EN" 
"http://java.sun.com/j2ee/dtds/web-jsptaglibrary_1_1.dtd"> 

<taglib> 
	<tlibversion>1.0</tlibversion> 
	<jspversion>1.1</jspversion>
	<shortname>DemoTags</shortname>
	<uri>http://www.stardeveloper.com</uri>
	<info>Demo Tags Library</info>

	<tag> 
		<name>firsttag</name> 
		<tagclass>com.stardeveloper.tag.test.FirstTag</tagclass>
		<bodycontent>empty</bodycontent>
		<info>Your first JSP Tag</info> 

		<attribute>
			<name>name</name>
			<required>false</required>
		</attribute>
	</tag> 
</taglib>
Explanation
Everything is packed between <taglib> and </taglib> tags. First five sub-tags under the <taglib> tag are general tags 
for providing info about the tag library as a whole. They are not specific to any tag.

<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE taglib PUBLIC "-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.1//EN" 
"http://java.sun.com/j2ee/dtds/web-jsptaglibrary_1_1.dtd"> 

<taglib>
tlibversion tag tells about the current version of tag library. Since this is the first time we built this demo 
tag library, I put 1.0 in it.

	<tlibversion>1.0</tlibversion>
jspversion tells the application server what version of JSP this tag library uses. There are currently three 
versions of JSP, 1.0, 1.1 and future version 1.2. Our JSP tag FirstTag uses JSP version 1.1.

	<jspversion>1.1</jspversion>
A simple short name for the tag library.

	<shortname>DemoTags</shortname>
uri tag doesn't do much job in the current version of JSP.

	<uri>http://www.stardeveloper.com</uri>
A short text about the use of this tag library.

	<info>Demo Tags Library</info>
Now comes individual tag description between the <tag> and </tag> tag.

	<tag> 
		<name>firsttag</name> 
		<tagclass>com.stardeveloper.tag.test.FirstTag</tagclass>
		<bodycontent>empty</bodycontent>
		<info>Your first JSP Tag</info> 

		<attribute>
			<name>name</name>
			<required>false</required>
		</attribute>
	</tag>
name is the short name you will be using in the JSP page after the prefix e.g. firsttag in the <star:firsttag /> tag.

tagclass should contain the complete path to the JSP tag class.

bodycontent should contain one of the three values; tagdependent, JSP and empty. Since there is body content 
in our tag so we select empty.

Optional info tag.

Then attribute tag describes each attribute separately. name is the name of the attribute, in our case it is "name". 
required can be true or false. We chose false so this tag can be used without entering value for the "name" attribute.

We have finished building the FirstTag.class and DemoTags.tld files. Let's now build the JSP page which calls this tag.

FirstTag.jsp JSP page
Create a new JSP page under the folder where you can run JSP pages. For this demo, I'll assume /web/jsp folder. 
Save this .jsp page as FirstTag.jsp and copy / paste the following code in it :

<html>
<head>
	<title>Your first JSP tag : FirstTag</title>
	<style>
	p, b { font-family:Tahoma,Sans-Serif; font-size:10pt; }
	b { font-weight:bold; }
	</style>
</head>
<body>

<p align="center">
<em><u>Your first JSP tag : FirstTag</u></em></p>

<%@ taglib uri="/WEB-INF/tlds/DemoTags.tld" prefix="star" %>
<p>Name entered : <star:firsttag name="Faisal Khan" /></p>

<p>No name entered : <star:firsttag /></p>

</body>
</html>
Explanation
We use the taglib directive to tell the application server we will be using a JSP tag in our JSP page. There are 
two attributes to taglib directive; uri and prefix. We set the uri attribute to the local address of DemoTags.tld 
TLD file. prefix attribute asks about the prefix we are going to use for this tag library, I chose "star".

<%@ taglib uri="/WEB-INF/tlds/DemoTags.tld" prefix="star" %>
Then we use the tag twice in the JSP page, first time we enter our name in the name attribute for the tag. Second 
time we don't enter anything in the name attribute.

<p>Name entered : <star:firsttag name="Faisal Khan" /></p>

<p>No name entered : <star:firsttag /></p>
Ok we are done with FirstTag.jsp JSP page. Now run using a URL like http://localhost:8080/web/jsp/FirstTag.jsp . 
To see the online demo click here.

You will notice that the tag gives different response depending on value of the "name" attribute.

*/