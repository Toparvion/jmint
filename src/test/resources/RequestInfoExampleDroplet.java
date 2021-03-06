/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.HTMLFilter;

/**
 * Example servlet showing request information.
 *
 * @author James Duncan Davidson <duncan@eng.sun.com>
 */

public class RequestInfoExampleDroplet extends HttpServlet {

  /**
   *
   * @param request
   * @param response
   * @throws IOException
   * @throws ServletException
   * @cutpoint AFTER
   */
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
          throws IOException, ServletException
  {
    PrintWriter out = response.getWriter();
    out.println("<h1>Droplets shine bright!</h1>");
  }

}

