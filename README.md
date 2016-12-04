# What is PowerBI-Embedded-Sample?
This sample offers to PowerBI Embedded Web application for Java and also includes some components such as JSF, CDI, logback.

# How to run this sample
Please following below steps.

- Download "Power BI Embedded - Integrate a report into a web app" sample and complete "Configure the sample app" section of [Get started with Power BI Embedded sample](https://docs.microsoft.com/en-us/azure/power-bi-embedded/power-bi-embedded-get-started-sample)
- After that, you have already registered your PowerBi workspace and pbix into Azure portal. Now, you can get [WORKSPACE ID], [WORKSPACE COLLECTION NAME] and [ACCESS TOKEN].
- Download this sample and edit src/main/resources/powerbiembedded.properties with your info.

Now, you can run this sample on Tomcat and other AppServers.

## Reference
- Get started with Power BI Embedded sample https://docs.microsoft.com/en-us/azure/power-bi-embedded/power-bi-embedded-get-started-sample
- How to use Power BI Embedded via REST https://blogs.msdn.microsoft.com/tsmatsuz/2016/07/20/power-bi-embedded-rest/

## Restriction
- You should use .NET Samples to create workspaces and register *.pbix files into Azure Portal

## Copyright
<table>
  <tr>
    <td>Copyright</td><td>Copyright (c) 2016- Daichi Isami</td>
  </tr>
  <tr>
    <td>License</td><td>MIT License</td>
  </tr>
</table>
