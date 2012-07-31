######################################################
# Makefile
######################################################

##### BINARIES   #####################################
COMPILER := javac #-verbose
LINKER := jar
DOC := javadoc
RM := rm -rf
CP := cp -R

LFLAG := -cfm

##### PROPERTIES #####################################
SRC_DIR := src
SRC := src/ori/ogapi/lists/*.java \
       src/ori/ogapi/util/*.java \
       src/ori/ogapi/report/*.java
LIB_DIR := lib
TARGET_DIR := target
TARGET := ogapi-core.jar
# for compiler
LIB_CP :=
# for linker
LIB :=
ENTRY_POINT :=
MANIFEST := MANIFEST.MF

##### DYNAMIC    #####################################
DEBUG_DIR := $(TARGET_DIR)/debug
RELEASE_DIR := $(TARGET_DIR)/release
TARGET_DEBUG := $(DEBUG_DIR)/$(TARGET)
TARGET_RELEASE := $(RELEASE_DIR)/$(TARGET)

##### TESTS ##########################################
SRC_TEST := src/tests/*.java
TARGET_TEST_DIR := $(TARGET_DIR)/tests
LIB_TEST := lib/junit-4.11.jar:$(DEBUG_DIR)/class


##### DOC PROPERTIES ##################################
DOC_DIR := $(RELEASE_DIR)/doc
PACKAGES := ori.ogapi.lists \
			ori.ogapi.util \
			ori.ogapi.report
WIN_TITLE := "OGAPI - core"
DOC_TITLE := "<b>OGAPI</b> - <i>core</i>"
DOC_TOP := "OGAPI"
DOC_BOT := "Origraena, 2011-2012"
DOC_SRC := $(SRC_DIR)


##### TARGET RULES ####################################

# all
all: debug release
#test release

# debug mode
debug: $(TARGET_DEBUG)

# release mode
release: $(TARGET_RELEASE) 
#doc

# test mode
#test: debug build-test exec-test

doc:
	$(DOC) -d $(DOC_DIR) \
	-sourcepath $(DOC_SRC) \
	-classpath $(LIB_CP) \
	-subpackages $(PACKAGES) \
	-use \
	-private \
	-windowtitle $(WIN_TITLE) \
	-doctitle $(DOC_TITLE) \
	-header $(DOC_TOP) \
	-bottom $(DOC_BOT)

# app file in debug mode
$(TARGET_DEBUG): obj-debug manifest
	@mkdir -p $(DEBUG_DIR)/class
	@echo "Invoking $(LINKER)..."
	$(LINKER) $(LFLAG) $(TARGET_DEBUG) MANIFEST.MF -C $(DEBUG_DIR)/class ./
#	$(CP) $(LIB_DIR) $(DEBUG_DIR)

# app file in release mode
$(TARGET_RELEASE): obj-release manifest
	@mkdir -p $(RELEASE_DIR)/class
	@echo "Invoking $(LINKER)..."
	$(LINKER) $(LFLAG) $(TARGET_RELEASE) MANIFEST.MF -C $(RELEASE_DIR)/class ./
#	$(CP) $(LIB_DIR) $(RELEASE_DIR)

# generate manifest file for jar
manifest:
	@echo "Generating manifest file : $(MANIFEST)..."
	@echo "Manifest-Version: 1.0" > $(MANIFEST)
	@echo "Main-Class: $(ENTRY_POINT)" >> $(MANIFEST)
#	@echo "Class-Path: $(LIB)" >> $(MANIFEST)

# object files in debug mode
obj-debug:
	@mkdir -p $(DEBUG_DIR)/class
	@echo "Invoking compiler..."
	$(COMPILER) -d $(DEBUG_DIR)/class $(SRC)

# object files in release mode
obj-release:
	@mkdir -p $(RELEASE_DIR)/class
	@echo "Invoking compiler..."
	$(COMPILER) -d $(RELEASE_DIR)/class $(SRC)

# tests

build-test:
	@mkdir -p $(TARGET_TEST_DIR)/class
	@echo "Invoking compiler..."
	$(COMPILER) -d $(TARGET_TEST_DIR)/class -cp $(LIB_TEST) $(SRC_TEST)

exec-test:
	@echo "Executing tests..."
	java -cp $(DEBUG_DIR)/class:$(TARGET_TEST_DIR)/class/:$(LIB_TEST) TestRunner


# delete only debug
clean:
	@echo 'Cleaning...'
	$(RM) $(DEBUG_DIR) $(MANIFEST)
	@echo 'Cleaned!'
	@clear

# delete all target files
mr-proper:
	@echo 'Full cleaning...'
	$(RM) $(TARGET_DIR) $(MANIFEST)
	@echo 'Fully cleaned!'
	@clear

