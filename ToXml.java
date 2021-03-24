//XML : element ��ü

DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

Document doc = docBuilder.newDocument();

// book element
Element movie = doc.createElement("movie");
doc.appendChild(movie);

// code element
Element movie = doc.createElement("code");
doc.appendChild(code);

//�Ӽ��� type = SF
code setAttribute("type", "SF");

// name Element
Element movieName = doc.createElement("movieName");
movieName.appendChild(doc.createTextNode("�׳�"));
code.appendChild(movieName);

// movieDirector element
Element movieDirector = doc.createElement("movieDirector");
movieDirector.appendChild(doc.createTextNode("ũ�������� ���"));
code.appendChild(movieDirector);

/*
<movie>
	<code type="SF">
		<movieName>�׳�</movieName>
		<movieDirector>ũ�������� ���</movieDirector>
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