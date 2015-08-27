/*
 * Copyright 2015 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.etosha.cmd;

import java.io.File;
import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options; 
import org.apache.avro.SchemaCompatibility;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;


/**
 *
 * @author training
 */
public class EFileSystemShell {

    public static void main(String[] args) throws IOException, ParseException {

        // create Options object
        Options options = new Options();
        
        // create a schema
        Schema schemaSRC = new Schema.Parser().parse(new File("resources/SrcTest.avsc"));
        Schema schemaDESTok = new Schema.Parser().parse(new File("resources/DestTestOK.avsc"));
        Schema schemaDESTfail = new Schema.Parser().parse(new File("resources/DestTestFail.avsc"));

        System.out.println( "[Namespace of test schema]" );
        
        System.out.println( "schemaSRC      : " + schemaSRC.getNamespace() );
        System.out.println( "schemaDESTok   : " + schemaDESTok.getNamespace() );
        System.out.println( "schemaDESTfail : " + schemaDESTfail.getNamespace() );
                
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse( options, args);
        
        System.out.println( "[CMD usage hints...]" );
        
        // add t option
        options.addOption("put", false, "Let's put a new file into a cluster, but only if the schema matches.");
        
        HelpFormatter hf=new HelpFormatter();
  
        hf.printHelp("efs-tool ",options,true);

        System.out.println( "Identity     : " + check( SchemaCompatibility.checkReaderWriterCompatibility(schemaSRC, schemaSRC) ) );
        System.out.println();
        System.out.println( "src  -> evo1 : " + check( SchemaCompatibility.checkReaderWriterCompatibility(schemaSRC, schemaDESTok)  )  );
        System.out.println();
        System.out.println( "evo1 -> src  : " + check( SchemaCompatibility.checkReaderWriterCompatibility(schemaDESTok, schemaSRC)  ));
        System.out.println();
        
        System.out.println( "src  -> evo2 : " + check( SchemaCompatibility.checkReaderWriterCompatibility(schemaSRC, schemaDESTfail) ) );
        System.out.println();
        System.out.println( "evo2 -> src  : " + check( SchemaCompatibility.checkReaderWriterCompatibility(schemaDESTfail, schemaSRC) ) );
        System.out.println();
        
    }

   

    private static String check(SchemaCompatibility.SchemaPairCompatibility c) {
        return c.getType().toString();
    }
}
