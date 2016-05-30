// Generated from C:/lang/dropper/src/main/java/ru/ftc/upc/testing/dropper/lang\DroppingJava.g4 by ANTLR 4.5.1
package ru.ftc.upc.testing.dropper.lang.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DroppingJavaParser}.
 */
public interface DroppingJavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(DroppingJavaParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(DroppingJavaParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#numericType}.
	 * @param ctx the parse tree
	 */
	void enterNumericType(DroppingJavaParser.NumericTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#numericType}.
	 * @param ctx the parse tree
	 */
	void exitNumericType(DroppingJavaParser.NumericTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#integralType}.
	 * @param ctx the parse tree
	 */
	void enterIntegralType(DroppingJavaParser.IntegralTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#integralType}.
	 * @param ctx the parse tree
	 */
	void exitIntegralType(DroppingJavaParser.IntegralTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#floatingPointType}.
	 * @param ctx the parse tree
	 */
	void enterFloatingPointType(DroppingJavaParser.FloatingPointTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#floatingPointType}.
	 * @param ctx the parse tree
	 */
	void exitFloatingPointType(DroppingJavaParser.FloatingPointTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceType(DroppingJavaParser.ReferenceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceType(DroppingJavaParser.ReferenceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceType(DroppingJavaParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceType(DroppingJavaParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(DroppingJavaParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(DroppingJavaParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classType_lf_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassType_lf_classOrInterfaceType(DroppingJavaParser.ClassType_lf_classOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classType_lf_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassType_lf_classOrInterfaceType(DroppingJavaParser.ClassType_lf_classOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassType_lfno_classOrInterfaceType(DroppingJavaParser.ClassType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassType_lfno_classOrInterfaceType(DroppingJavaParser.ClassType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceType}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceType(DroppingJavaParser.InterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceType}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceType(DroppingJavaParser.InterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceType_lf_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceType_lf_classOrInterfaceType(DroppingJavaParser.InterfaceType_lf_classOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceType_lf_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceType_lf_classOrInterfaceType(DroppingJavaParser.InterfaceType_lf_classOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceType_lfno_classOrInterfaceType(DroppingJavaParser.InterfaceType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceType_lfno_classOrInterfaceType(DroppingJavaParser.InterfaceType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void enterTypeVariable(DroppingJavaParser.TypeVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void exitTypeVariable(DroppingJavaParser.TypeVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(DroppingJavaParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(DroppingJavaParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#dims}.
	 * @param ctx the parse tree
	 */
	void enterDims(DroppingJavaParser.DimsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#dims}.
	 * @param ctx the parse tree
	 */
	void exitDims(DroppingJavaParser.DimsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(DroppingJavaParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(DroppingJavaParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeParameterModifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterModifier(DroppingJavaParser.TypeParameterModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeParameterModifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterModifier(DroppingJavaParser.TypeParameterModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void enterTypeBound(DroppingJavaParser.TypeBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void exitTypeBound(DroppingJavaParser.TypeBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#additionalBound}.
	 * @param ctx the parse tree
	 */
	void enterAdditionalBound(DroppingJavaParser.AdditionalBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#additionalBound}.
	 * @param ctx the parse tree
	 */
	void exitAdditionalBound(DroppingJavaParser.AdditionalBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(DroppingJavaParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(DroppingJavaParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeArgumentList}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentList(DroppingJavaParser.TypeArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeArgumentList}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentList(DroppingJavaParser.TypeArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(DroppingJavaParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(DroppingJavaParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void enterWildcard(DroppingJavaParser.WildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void exitWildcard(DroppingJavaParser.WildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#wildcardBounds}.
	 * @param ctx the parse tree
	 */
	void enterWildcardBounds(DroppingJavaParser.WildcardBoundsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#wildcardBounds}.
	 * @param ctx the parse tree
	 */
	void exitWildcardBounds(DroppingJavaParser.WildcardBoundsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(DroppingJavaParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(DroppingJavaParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#packageOrTypeName}.
	 * @param ctx the parse tree
	 */
	void enterPackageOrTypeName(DroppingJavaParser.PackageOrTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#packageOrTypeName}.
	 * @param ctx the parse tree
	 */
	void exitPackageOrTypeName(DroppingJavaParser.PackageOrTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(DroppingJavaParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(DroppingJavaParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(DroppingJavaParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(DroppingJavaParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#packageModifier}.
	 * @param ctx the parse tree
	 */
	void enterPackageModifier(DroppingJavaParser.PackageModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#packageModifier}.
	 * @param ctx the parse tree
	 */
	void exitPackageModifier(DroppingJavaParser.PackageModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(DroppingJavaParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(DroppingJavaParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleTypeImportDeclaration(DroppingJavaParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleTypeImportDeclaration(DroppingJavaParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeImportOnDemandDeclaration(DroppingJavaParser.TypeImportOnDemandDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeImportOnDemandDeclaration(DroppingJavaParser.TypeImportOnDemandDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleStaticImportDeclaration(DroppingJavaParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleStaticImportDeclaration(DroppingJavaParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#staticImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStaticImportOnDemandDeclaration(DroppingJavaParser.StaticImportOnDemandDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#staticImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStaticImportOnDemandDeclaration(DroppingJavaParser.StaticImportOnDemandDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(DroppingJavaParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(DroppingJavaParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(DroppingJavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(DroppingJavaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#normalClassDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#normalClassDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassModifier(DroppingJavaParser.ClassModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassModifier(DroppingJavaParser.ClassModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(DroppingJavaParser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(DroppingJavaParser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#typeParameterList}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterList(DroppingJavaParser.TypeParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#typeParameterList}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterList(DroppingJavaParser.TypeParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#superclass}.
	 * @param ctx the parse tree
	 */
	void enterSuperclass(DroppingJavaParser.SuperclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#superclass}.
	 * @param ctx the parse tree
	 */
	void exitSuperclass(DroppingJavaParser.SuperclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#superinterfaces}.
	 * @param ctx the parse tree
	 */
	void enterSuperinterfaces(DroppingJavaParser.SuperinterfacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#superinterfaces}.
	 * @param ctx the parse tree
	 */
	void exitSuperinterfaces(DroppingJavaParser.SuperinterfacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceTypeList}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceTypeList(DroppingJavaParser.InterfaceTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceTypeList}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceTypeList(DroppingJavaParser.InterfaceTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(DroppingJavaParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(DroppingJavaParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(DroppingJavaParser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(DroppingJavaParser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#classMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassMemberDeclaration(DroppingJavaParser.ClassMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#classMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassMemberDeclaration(DroppingJavaParser.ClassMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(DroppingJavaParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(DroppingJavaParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#fieldModifier}.
	 * @param ctx the parse tree
	 */
	void enterFieldModifier(DroppingJavaParser.FieldModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#fieldModifier}.
	 * @param ctx the parse tree
	 */
	void exitFieldModifier(DroppingJavaParser.FieldModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorList(DroppingJavaParser.VariableDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorList(DroppingJavaParser.VariableDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(DroppingJavaParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(DroppingJavaParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(DroppingJavaParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(DroppingJavaParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(DroppingJavaParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(DroppingJavaParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannType}.
	 * @param ctx the parse tree
	 */
	void enterUnannType(DroppingJavaParser.UnannTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannType}.
	 * @param ctx the parse tree
	 */
	void exitUnannType(DroppingJavaParser.UnannTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannPrimitiveType}.
	 * @param ctx the parse tree
	 */
	void enterUnannPrimitiveType(DroppingJavaParser.UnannPrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannPrimitiveType}.
	 * @param ctx the parse tree
	 */
	void exitUnannPrimitiveType(DroppingJavaParser.UnannPrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannReferenceType}.
	 * @param ctx the parse tree
	 */
	void enterUnannReferenceType(DroppingJavaParser.UnannReferenceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannReferenceType}.
	 * @param ctx the parse tree
	 */
	void exitUnannReferenceType(DroppingJavaParser.UnannReferenceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterUnannClassOrInterfaceType(DroppingJavaParser.UnannClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitUnannClassOrInterfaceType(DroppingJavaParser.UnannClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannClassType_lf_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterUnannClassType_lf_unannClassOrInterfaceType(DroppingJavaParser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannClassType_lf_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitUnannClassType_lf_unannClassOrInterfaceType(DroppingJavaParser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannClassType_lfno_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterUnannClassType_lfno_unannClassOrInterfaceType(DroppingJavaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannClassType_lfno_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitUnannClassType_lfno_unannClassOrInterfaceType(DroppingJavaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannInterfaceType_lf_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterUnannInterfaceType_lf_unannClassOrInterfaceType(DroppingJavaParser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannInterfaceType_lf_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitUnannInterfaceType_lf_unannClassOrInterfaceType(DroppingJavaParser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannInterfaceType_lfno_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterUnannInterfaceType_lfno_unannClassOrInterfaceType(DroppingJavaParser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannInterfaceType_lfno_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitUnannInterfaceType_lfno_unannClassOrInterfaceType(DroppingJavaParser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannTypeVariable}.
	 * @param ctx the parse tree
	 */
	void enterUnannTypeVariable(DroppingJavaParser.UnannTypeVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannTypeVariable}.
	 * @param ctx the parse tree
	 */
	void exitUnannTypeVariable(DroppingJavaParser.UnannTypeVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#unannArrayType}.
	 * @param ctx the parse tree
	 */
	void enterUnannArrayType(DroppingJavaParser.UnannArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#unannArrayType}.
	 * @param ctx the parse tree
	 */
	void exitUnannArrayType(DroppingJavaParser.UnannArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(DroppingJavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(DroppingJavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#methodModifier}.
	 * @param ctx the parse tree
	 */
	void enterMethodModifier(DroppingJavaParser.MethodModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#methodModifier}.
	 * @param ctx the parse tree
	 */
	void exitMethodModifier(DroppingJavaParser.MethodModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#methodHeader}.
	 * @param ctx the parse tree
	 */
	void enterMethodHeader(DroppingJavaParser.MethodHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#methodHeader}.
	 * @param ctx the parse tree
	 */
	void exitMethodHeader(DroppingJavaParser.MethodHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#result}.
	 * @param ctx the parse tree
	 */
	void enterResult(DroppingJavaParser.ResultContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#result}.
	 * @param ctx the parse tree
	 */
	void exitResult(DroppingJavaParser.ResultContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#methodDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclarator(DroppingJavaParser.MethodDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#methodDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclarator(DroppingJavaParser.MethodDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(DroppingJavaParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(DroppingJavaParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(DroppingJavaParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(DroppingJavaParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(DroppingJavaParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(DroppingJavaParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(DroppingJavaParser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(DroppingJavaParser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void enterLastFormalParameter(DroppingJavaParser.LastFormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void exitLastFormalParameter(DroppingJavaParser.LastFormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#receiverParameter}.
	 * @param ctx the parse tree
	 */
	void enterReceiverParameter(DroppingJavaParser.ReceiverParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#receiverParameter}.
	 * @param ctx the parse tree
	 */
	void exitReceiverParameter(DroppingJavaParser.ReceiverParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#throws_}.
	 * @param ctx the parse tree
	 */
	void enterThrows_(DroppingJavaParser.Throws_Context ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#throws_}.
	 * @param ctx the parse tree
	 */
	void exitThrows_(DroppingJavaParser.Throws_Context ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#exceptionTypeList}.
	 * @param ctx the parse tree
	 */
	void enterExceptionTypeList(DroppingJavaParser.ExceptionTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#exceptionTypeList}.
	 * @param ctx the parse tree
	 */
	void exitExceptionTypeList(DroppingJavaParser.ExceptionTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#exceptionType}.
	 * @param ctx the parse tree
	 */
	void enterExceptionType(DroppingJavaParser.ExceptionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#exceptionType}.
	 * @param ctx the parse tree
	 */
	void exitExceptionType(DroppingJavaParser.ExceptionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(DroppingJavaParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(DroppingJavaParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#instanceInitializer}.
	 * @param ctx the parse tree
	 */
	void enterInstanceInitializer(DroppingJavaParser.InstanceInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#instanceInitializer}.
	 * @param ctx the parse tree
	 */
	void exitInstanceInitializer(DroppingJavaParser.InstanceInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#staticInitializer}.
	 * @param ctx the parse tree
	 */
	void enterStaticInitializer(DroppingJavaParser.StaticInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#staticInitializer}.
	 * @param ctx the parse tree
	 */
	void exitStaticInitializer(DroppingJavaParser.StaticInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(DroppingJavaParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(DroppingJavaParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#constructorModifier}.
	 * @param ctx the parse tree
	 */
	void enterConstructorModifier(DroppingJavaParser.ConstructorModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#constructorModifier}.
	 * @param ctx the parse tree
	 */
	void exitConstructorModifier(DroppingJavaParser.ConstructorModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#constructorDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclarator(DroppingJavaParser.ConstructorDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#constructorDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclarator(DroppingJavaParser.ConstructorDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#simpleTypeName}.
	 * @param ctx the parse tree
	 */
	void enterSimpleTypeName(DroppingJavaParser.SimpleTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#simpleTypeName}.
	 * @param ctx the parse tree
	 */
	void exitSimpleTypeName(DroppingJavaParser.SimpleTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void enterConstructorBody(DroppingJavaParser.ConstructorBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void exitConstructorBody(DroppingJavaParser.ConstructorBodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EnumName}
	 * labeled alternative in {@link DroppingJavaParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumName(DroppingJavaParser.EnumNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EnumName}
	 * labeled alternative in {@link DroppingJavaParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumName(DroppingJavaParser.EnumNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#enumBody}.
	 * @param ctx the parse tree
	 */
	void enterEnumBody(DroppingJavaParser.EnumBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#enumBody}.
	 * @param ctx the parse tree
	 */
	void exitEnumBody(DroppingJavaParser.EnumBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#enumConstantList}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstantList(DroppingJavaParser.EnumConstantListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#enumConstantList}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstantList(DroppingJavaParser.EnumConstantListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstant(DroppingJavaParser.EnumConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstant(DroppingJavaParser.EnumConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#enumConstantModifier}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstantModifier(DroppingJavaParser.EnumConstantModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#enumConstantModifier}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstantModifier(DroppingJavaParser.EnumConstantModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterEnumBodyDeclarations(DroppingJavaParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitEnumBodyDeclarations(DroppingJavaParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(DroppingJavaParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(DroppingJavaParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(DroppingJavaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(DroppingJavaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(DroppingJavaParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(DroppingJavaParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(DroppingJavaParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(DroppingJavaParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfaceName}
	 * labeled alternative in {@link DroppingJavaParser#normalInterfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterIfaceName(DroppingJavaParser.IfaceNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfaceName}
	 * labeled alternative in {@link DroppingJavaParser#normalInterfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitIfaceName(DroppingJavaParser.IfaceNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceModifier}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceModifier(DroppingJavaParser.InterfaceModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceModifier}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceModifier(DroppingJavaParser.InterfaceModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#extendsInterfaces}.
	 * @param ctx the parse tree
	 */
	void enterExtendsInterfaces(DroppingJavaParser.ExtendsInterfacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#extendsInterfaces}.
	 * @param ctx the parse tree
	 */
	void exitExtendsInterfaces(DroppingJavaParser.ExtendsInterfacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(DroppingJavaParser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(DroppingJavaParser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDeclaration(DroppingJavaParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDeclaration(DroppingJavaParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaration(DroppingJavaParser.ConstantDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaration(DroppingJavaParser.ConstantDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#constantModifier}.
	 * @param ctx the parse tree
	 */
	void enterConstantModifier(DroppingJavaParser.ConstantModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#constantModifier}.
	 * @param ctx the parse tree
	 */
	void exitConstantModifier(DroppingJavaParser.ConstantModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodDeclaration(DroppingJavaParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodDeclaration(DroppingJavaParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#interfaceMethodModifier}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodModifier(DroppingJavaParser.InterfaceMethodModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#interfaceMethodModifier}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodModifier(DroppingJavaParser.InterfaceMethodModifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnoName}
	 * labeled alternative in {@link DroppingJavaParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnoName(DroppingJavaParser.AnnoNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnoName}
	 * labeled alternative in {@link DroppingJavaParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnoName(DroppingJavaParser.AnnoNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeBody(DroppingJavaParser.AnnotationTypeBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeBody(DroppingJavaParser.AnnotationTypeBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(DroppingJavaParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(DroppingJavaParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#normalAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterNormalAnnotation(DroppingJavaParser.NormalAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#normalAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitNormalAnnotation(DroppingJavaParser.NormalAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#elementValuePairList}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairList(DroppingJavaParser.ElementValuePairListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#elementValuePairList}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairList(DroppingJavaParser.ElementValuePairListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(DroppingJavaParser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(DroppingJavaParser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(DroppingJavaParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(DroppingJavaParser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterElementValueArrayInitializer(DroppingJavaParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitElementValueArrayInitializer(DroppingJavaParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#elementValueList}.
	 * @param ctx the parse tree
	 */
	void enterElementValueList(DroppingJavaParser.ElementValueListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#elementValueList}.
	 * @param ctx the parse tree
	 */
	void exitElementValueList(DroppingJavaParser.ElementValueListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#markerAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterMarkerAnnotation(DroppingJavaParser.MarkerAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#markerAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitMarkerAnnotation(DroppingJavaParser.MarkerAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterSingleElementAnnotation(DroppingJavaParser.SingleElementAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitSingleElementAnnotation(DroppingJavaParser.SingleElementAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(DroppingJavaParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(DroppingJavaParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#variableInitializerList}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializerList(DroppingJavaParser.VariableInitializerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#variableInitializerList}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializerList(DroppingJavaParser.VariableInitializerListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(DroppingJavaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(DroppingJavaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroppingJavaParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatements(DroppingJavaParser.BlockStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroppingJavaParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatements(DroppingJavaParser.BlockStatementsContext ctx);
}