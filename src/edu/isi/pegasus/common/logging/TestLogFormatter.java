/**
 *  Copyright 2007-2008 University Of Southern California
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package edu.isi.pegasus.common.logging;



/**
 * Test program to test out LogFormatter API
 *
 * @author Karan Vahi
 * 
 * @version $Revision$
 */
public class TestLogFormatter {
    
    /**
     * 
     * @param fm  the LOG formatter to use.
     */
    public void writeTestLog( LogFormatter fm ){
        fm.setProgramName( "Pegasus" );
            
            
        fm.addEvent( "event.pegasus.ranking", LoggingKeys.DAX_ID, "se18-gda.dax" );              
        System.out.println( fm.getStartEventMessage() );
            
        /* nesting events */
        fm.addEvent( "event.pegasus.parsing.dax", LoggingKeys.DAX_ID, "se18-gda-nested.dax" );
        System.out.println( fm.getStartEventMessage() );
        System.out.println( fm.getEndEventMessage() );            
        fm.popEvent();
            
        fm.add( LoggingKeys.JOB_ID, "jobGDA" );
        System.out.println( fm.createLogMessage() );
        fm.add( LoggingKeys.QUERY_NAME, "getpredicted performace" ).add( "time", "10.00" );
        System.out.println( fm.createLogMessageAndReset() );
            
        System.out.println( fm.getEndEventMessage() );
    }
  
    public static void main(String[] args) {
        TestLogFormatter test = new TestLogFormatter();
        System.out.println("\n Logs in Netlogger Format");
        test.writeTestLog( LogFormatterFactory.loadInstance( "Netlogger") );

        System.out.println("\n Logs in Simple Format");
        test.writeTestLog( LogFormatterFactory.loadInstance( "Simple") );

    }
}
