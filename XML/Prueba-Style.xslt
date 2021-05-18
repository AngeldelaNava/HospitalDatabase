<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>Hospital Workers' Report</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
      <th>Name</th>
      <th>Salary</th>
      <th>Job</th>       
    </tr>
    <xsl:for-each select="Worker">
    <xsl:if test="salary>1200">
      <tr>
        <td><xsl:value-of select="name"/></td>
        <td><xsl:value-of select="salary"/></td>
        <td><xsl:value-of select="job"/></td>        
      </tr>
    </xsl:if>
    </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:transform>