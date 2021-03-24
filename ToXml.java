//XML : element 객체

DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

Document doc = docBuilder.newDocument();

// book element
Element movie = doc.createElement("movie");
doc.appendChild(movie);

// code element
Element movie = doc.createElement("code");
doc.appendChild(code);

//속성값 type = SF
code setAttribute("type", "SF");

// name Element
Element movieName = doc.createElement("movieName");
movieName.appendChild(doc.createTextNode("테넷"));
code.appendChild(movieName);

// movieDirector element
Element movieDirector = doc.createElement("movieDirector");
movieDirector.appendChild(doc.createTextNode("크리스토퍼 놀란"));
code.appendChild(movieDirector);

/*
<movie>
	<code type="SF">
		<movieName>테넷</movieName>
		<movieDirector>크리스토퍼 놀란</movieDirector>
	</code>
</movie>
*/

//XML : file write

TransformerFactory TransformerFactory = TransformerFactory.newInstance();

Transformer transformer = TransformerFactory.newTransformer();
transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

DOMSource source = new DOMSource(doc);
//StreamResult result = new StreamResult(new FileOutputStream(new File("src/data/book.xml")));

StreamResult result = new StreamResult(new FileOutputStream(new File("src/collection.xml")));

transformer.transform(source, result);