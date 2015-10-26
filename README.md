<h5>Parsing XML to a Shape and print info about it in console</h5>
<p>The application parse XML file and print in console the ordinal number of each shape, it's color and square.<p/>
<p>The name of the xml file has to be transmitted as the first parameter when the application is starting</p>
<p>The input file's schema must match <a href = "https://github.com/timurnav/XmlParser/blob/master/src/main/resources/shapes.xsd">this</a>
<p>Output format is:
<strong><pre>&lt;i&gt;: &lt;color&gt; - &lt;area&gt;\n</strong></pre><p>
where</br>
<ul><li> &lt;i&gt; - ordinal number;</li>
<li>&lt;color&gt; - the value of tag <color> of this shape</li>
<li>&lt;area&gt; - the square of this shape if format #.##</li>
