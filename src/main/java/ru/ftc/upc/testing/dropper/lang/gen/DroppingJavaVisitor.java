// Generated from C:/lang/dropper/src/main/java/ru/ftc/upc/testing/dropper/lang\DroppingJava.g4 by ANTLR 4.5.1
package ru.ftc.upc.testing.dropper.lang.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DroppingJavaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DroppingJavaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(DroppingJavaParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#numericType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericType(DroppingJavaParser.NumericTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#integralType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegralType(DroppingJavaParser.IntegralTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#floatingPointType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatingPointType(DroppingJavaParser.FloatingPointTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#referenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceType(DroppingJavaParser.ReferenceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassOrInterfaceType(DroppingJavaParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType(DroppingJavaParser.ClassTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classType_lf_classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType_lf_classOrInterfaceType(DroppingJavaParser.ClassType_lf_classOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType_lfno_classOrInterfaceType(DroppingJavaParser.ClassType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceType(DroppingJavaParser.InterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceType_lf_classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceType_lf_classOrInterfaceType(DroppingJavaParser.InterfaceType_lf_classOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceType_lfno_classOrInterfaceType(DroppingJavaParser.InterfaceType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeVariable(DroppingJavaParser.TypeVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(DroppingJavaParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#dims}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDims(DroppingJavaParser.DimsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameter(DroppingJavaParser.TypeParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeParameterModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameterModifier(DroppingJavaParser.TypeParameterModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBound(DroppingJavaParser.TypeBoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#additionalBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionalBound(DroppingJavaParser.AdditionalBoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArguments(DroppingJavaParser.TypeArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeArgumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgumentList(DroppingJavaParser.TypeArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgument(DroppingJavaParser.TypeArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#wildcard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard(DroppingJavaParser.WildcardContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#wildcardBounds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcardBounds(DroppingJavaParser.WildcardBoundsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(DroppingJavaParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#packageOrTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageOrTypeName(DroppingJavaParser.PackageOrTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(DroppingJavaParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#packageDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDeclaration(DroppingJavaParser.PackageDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#packageModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageModifier(DroppingJavaParser.PackageModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(DroppingJavaParser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleTypeImportDeclaration(DroppingJavaParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeImportOnDemandDeclaration(DroppingJavaParser.TypeImportOnDemandDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleStaticImportDeclaration(DroppingJavaParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#staticImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaticImportOnDemandDeclaration(DroppingJavaParser.StaticImportOnDemandDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(DroppingJavaParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(DroppingJavaParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#normalClassDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalClassDeclaration(DroppingJavaParser.NormalClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassModifier(DroppingJavaParser.ClassModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameters(DroppingJavaParser.TypeParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#typeParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameterList(DroppingJavaParser.TypeParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#superclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperclass(DroppingJavaParser.SuperclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#superinterfaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperinterfaces(DroppingJavaParser.SuperinterfacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceTypeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceTypeList(DroppingJavaParser.InterfaceTypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(DroppingJavaParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBodyDeclaration(DroppingJavaParser.ClassBodyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#classMemberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMemberDeclaration(DroppingJavaParser.ClassMemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(DroppingJavaParser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#fieldModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldModifier(DroppingJavaParser.FieldModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorList(DroppingJavaParser.VariableDeclaratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(DroppingJavaParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorId(DroppingJavaParser.VariableDeclaratorIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(DroppingJavaParser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannType(DroppingJavaParser.UnannTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannPrimitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannPrimitiveType(DroppingJavaParser.UnannPrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannReferenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannReferenceType(DroppingJavaParser.UnannReferenceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannClassOrInterfaceType(DroppingJavaParser.UnannClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannClassType_lf_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannClassType_lf_unannClassOrInterfaceType(DroppingJavaParser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannClassType_lfno_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannClassType_lfno_unannClassOrInterfaceType(DroppingJavaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannInterfaceType_lf_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannInterfaceType_lf_unannClassOrInterfaceType(DroppingJavaParser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannInterfaceType_lfno_unannClassOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannInterfaceType_lfno_unannClassOrInterfaceType(DroppingJavaParser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannTypeVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannTypeVariable(DroppingJavaParser.UnannTypeVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#unannArrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnannArrayType(DroppingJavaParser.UnannArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(DroppingJavaParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#methodModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodModifier(DroppingJavaParser.MethodModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#methodHeader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodHeader(DroppingJavaParser.MethodHeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#result}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResult(DroppingJavaParser.ResultContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#methodDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclarator(DroppingJavaParser.MethodDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(DroppingJavaParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(DroppingJavaParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(DroppingJavaParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#variableModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableModifier(DroppingJavaParser.VariableModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLastFormalParameter(DroppingJavaParser.LastFormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#receiverParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceiverParameter(DroppingJavaParser.ReceiverParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#throws_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrows_(DroppingJavaParser.Throws_Context ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#exceptionTypeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExceptionTypeList(DroppingJavaParser.ExceptionTypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#exceptionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExceptionType(DroppingJavaParser.ExceptionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(DroppingJavaParser.MethodBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#instanceInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceInitializer(DroppingJavaParser.InstanceInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#staticInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaticInitializer(DroppingJavaParser.StaticInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(DroppingJavaParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#constructorModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorModifier(DroppingJavaParser.ConstructorModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#constructorDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclarator(DroppingJavaParser.ConstructorDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#simpleTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleTypeName(DroppingJavaParser.SimpleTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#constructorBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorBody(DroppingJavaParser.ConstructorBodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EnumName}
	 * labeled alternative in {@link DroppingJavaParser#enumDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumName(DroppingJavaParser.EnumNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#enumBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumBody(DroppingJavaParser.EnumBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#enumConstantList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConstantList(DroppingJavaParser.EnumConstantListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#enumConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConstant(DroppingJavaParser.EnumConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#enumConstantModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumConstantModifier(DroppingJavaParser.EnumConstantModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumBodyDeclarations(DroppingJavaParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(DroppingJavaParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(DroppingJavaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(DroppingJavaParser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDeclaration(DroppingJavaParser.InterfaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfaceName}
	 * labeled alternative in {@link DroppingJavaParser#normalInterfaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfaceName(DroppingJavaParser.IfaceNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceModifier(DroppingJavaParser.InterfaceModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#extendsInterfaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsInterfaces(DroppingJavaParser.ExtendsInterfacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBody(DroppingJavaParser.InterfaceBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMemberDeclaration(DroppingJavaParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#constantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaration(DroppingJavaParser.ConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#constantModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantModifier(DroppingJavaParser.ConstantModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMethodDeclaration(DroppingJavaParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#interfaceMethodModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMethodModifier(DroppingJavaParser.InterfaceMethodModifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AnnoName}
	 * labeled alternative in {@link DroppingJavaParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnoName(DroppingJavaParser.AnnoNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationTypeBody(DroppingJavaParser.AnnotationTypeBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(DroppingJavaParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#normalAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalAnnotation(DroppingJavaParser.NormalAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#elementValuePairList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValuePairList(DroppingJavaParser.ElementValuePairListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#elementValuePair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValuePair(DroppingJavaParser.ElementValuePairContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#elementValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValue(DroppingJavaParser.ElementValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValueArrayInitializer(DroppingJavaParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#elementValueList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValueList(DroppingJavaParser.ElementValueListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#markerAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMarkerAnnotation(DroppingJavaParser.MarkerAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleElementAnnotation(DroppingJavaParser.SingleElementAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(DroppingJavaParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#variableInitializerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializerList(DroppingJavaParser.VariableInitializerListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DroppingJavaParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroppingJavaParser#blockStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatements(DroppingJavaParser.BlockStatementsContext ctx);
}