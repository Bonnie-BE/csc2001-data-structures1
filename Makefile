JAVAC = javac
JFLAGS = -g
SRCDIR = src
BINDIR = bin
DOCDIR = doc

.SUFFIXES: .java .class

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR) -cp $(BINDIR) $<

CLASSES= $(BINDIR)/Generic.class $(BINDIR)/GenericsKbArrayApp.class\
	 $(BINDIR)/GenericsKbBSTApp.class\

default: $(CLASSES)

clean:
	rm $(BINDIR)/*.class

runArray:
	java -cp $(BINDIR) GenericsKbArrayApp
runBST:
	java -cp $(BINDIR) GenericsKbBSTApp

javadoc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java
